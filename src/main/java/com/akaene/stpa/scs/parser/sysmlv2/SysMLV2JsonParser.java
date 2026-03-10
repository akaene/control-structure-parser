package com.akaene.stpa.scs.parser.sysmlv2;

import com.akaene.stpa.scs.exception.ControlStructureParserException;
import com.akaene.stpa.scs.model.Component;
import com.akaene.stpa.scs.model.ComponentType;
import com.akaene.stpa.scs.model.Connector;
import com.akaene.stpa.scs.model.ConnectorEnd;
import com.akaene.stpa.scs.model.Model;
import com.akaene.stpa.scs.model.Stereotype;
import com.akaene.stpa.scs.parser.ControlStructureParser;
import com.akaene.stpa.scs.parser.sysmlv2.model.FlowConnectionDefinition;
import com.akaene.stpa.scs.parser.sysmlv2.model.FlowConnectionUsage;
import com.akaene.stpa.scs.parser.sysmlv2.model.NamedElement;
import com.akaene.stpa.scs.parser.sysmlv2.model.ObjectIdentity;
import com.akaene.stpa.scs.parser.sysmlv2.model.OwnedElement;
import com.akaene.stpa.scs.parser.sysmlv2.model.Package;
import com.akaene.stpa.scs.parser.sysmlv2.model.PartDefinition;
import com.akaene.stpa.scs.parser.sysmlv2.model.PartUsage;
import com.akaene.stpa.scs.parser.sysmlv2.model.SysMLV2Element;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Parses control structure from a SysML V2 JSON artifact.
 * <p>
 * The parser supports SysML V2 JSON artifacts with {@literal .json} extension from the response
 * of HTTP requests described in section "REST/HTTP Platform Specific Model (PSM)" of the
 * "Systems Modeling Application Programming Interface (API) and Services" (SysML V2) specification
 * available at <a href="https://www.omg.org/spec/SystemsModelingAPI/1.0/">SystemsModelingAPI</a>.
 * </p>
 * <p>
 * The artifacts in this format are available from the REST endpoint
 * GET /projects/{projectId}/commits/{commitId}/elements
 * </p>
 *
 * Parser design decisions:
 * <ul>
 *     <li> name model according to root package name -- if project contains more than one package, the that does not
 *     have parent package is chosen to name the model</li>
 * </ul>
 */
public class SysMLV2JsonParser implements ControlStructureParser {

    private static final Logger LOG = LoggerFactory.getLogger(SysMLV2JsonParser.class);
    public static String SUPPORTED_FILE_EXTENSION = ".json";

    private final ObjectMapper objectMapper;

    public SysMLV2JsonParser() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.addHandler(new SysMLV2DeserializationProblemHandler());
    }

    @Override
    public Model parse(File input) {
        try {
            JsonNode rootNode = objectMapper.readTree(input);

            if (!rootNode.isArray()) {
                throw new ControlStructureParserException("Expected JSON array of SysMLv2 elements, but got: " +
                        rootNode.getNodeType());
            }

            if (rootNode.isEmpty()) {
                throw new ControlStructureParserException("JSON array is empty");
            }

            // Parse the entire array into a List<SysMLv2Element>
            List<SysMLV2Element> elements = objectMapper
                    .readerFor(new TypeReference<List<SysMLV2Element>>() {
                    })
                    .readValue(rootNode);

            Model model = new Model();
            Map<String, Component> componentMap = new HashMap<>();
            Map<String, ComponentType> componentTypeMap = new HashMap<>();
            Map<String, Stereotype> connectorStereotypesMap = new HashMap<>();

            // load class types such as ControlledProcess, Controller, Sensor, Actuator
            elements.stream()
                    .filter(PartDefinition.class::isInstance)
                    .map(PartDefinition.class::cast)
                    .forEach(
                            pd -> {
                                ComponentType componentType = new ComponentType(pd.getName(), pd.getQualifiedName()); 
                                componentTypeMap.put(pd.getId(), componentType);
                                model.addClass(componentType);
                            }
                    );

            // load connector stereotypes such as ControlAction, Feedback, ProcessInput
            elements.stream()
                    .filter(FlowConnectionDefinition.class::isInstance)
                    .map(FlowConnectionDefinition.class::cast)
                    .forEach(
                            fcd -> {
                                Stereotype stereotype = new Stereotype(fcd.getName());
                                connectorStereotypesMap.put(fcd.getId(), stereotype);
                                model.addStereotype(stereotype);
                                LOG.debug("Loading connector stereotype {} with id {}", fcd.getName(), fcd.getId());
                            }
                    );

            // load components
            elements.stream()
                    .filter(PartUsage.class::isInstance)
                    .map(PartUsage.class::cast)
                    .forEach(
                            pu -> {


                                ComponentType componentType= ensureAtMostOneComponentType(pu.getPartDefinition(), componentTypeMap);
                                Component component = new Component(pu.getName(), pu.getQualifiedName(), componentType, pu.getIdentifier());

                                componentMap.put(pu.getId(), component);
                                model.addComponent(component);
                            }
                    );

            // load connectors
            elements.stream()
                    .filter(FlowConnectionUsage.class::isInstance)
                    .map(FlowConnectionUsage.class::cast)
                    .forEach(
                            fcu -> {
                                Component sourceComponent = ensureOneComponent(fcu.getSource(), componentMap);
                                Component targetComponent = ensureOneComponent(fcu.getTarget(), componentMap);

                                ConnectorEnd source = new ConnectorEnd(sourceComponent, null, null, null);
                                ConnectorEnd target = new ConnectorEnd(targetComponent, null, null, null);
                                Connector connector = new Connector(fcu.getName(), fcu.getQualifiedName(), source, target, fcu.getIdentifier());

                                fcu.getConnectionDefinition().stream()
                                   .map(cd -> connectorStereotypesMap.get(cd.getId()))
                                   .forEach(connector::addStereotype);
                                model.addConnector(connector);
                            }
                    );

            // load partonomy of components
            elements.stream()
                    .filter(PartUsage.class::isInstance)
                    .map(PartUsage.class::cast)
                    .forEach(
                            pu -> {
                                Component component = componentMap.get(pu.getId());
                                Optional.ofNullable(componentMap.get(pu.getOwner().getId()))
                                        .ifPresent(component::setParent);
                            }
                    );

            model.setName(
                    getModelNameFromRootPackage(elements)
            );

            return model;

        } catch (IOException e) {
            throw new ControlStructureParserException("Failed to parse SysML V2 JSON file: " + input.getName(), e);
        }
    }

    private String getModelNameFromRootPackage(List<SysMLV2Element> elements) {
        return elements.stream()
                       .filter(Package.class::isInstance)
                       .map(Package.class::cast)
                       .filter(p -> p.getOwner() == null)
                       .map(Package::getName)
                       .findFirst()
                       .orElseThrow(() -> new ControlStructureParserException("No root package found to retrieve model name."));
    }

    private Component ensureOneComponent(List<ObjectIdentity> componentList, Map<String, Component> componentMap) {
        if (componentList.size() != 1) {
            throw new ControlStructureParserException(
                    "Expected exactly one component, but got " + componentList.size()
                            + " for component list " + componentList
            );
        }
        return componentList.stream()
                .map(oi -> componentMap.get(oi.getId()))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new ControlStructureParserException(
                        "Component not found for id " + componentList.getFirst().getId()
                        )
                );
    }

    private ComponentType ensureAtMostOneComponentType(List<ObjectIdentity> componentTypeList, Map<String, ComponentType> componentTypeMap) {
        if (componentTypeList.isEmpty()) {
            return null;
        }
        if (componentTypeList.size() > 1) {
            throw new ControlStructureParserException(
                    "Expected at most one component type, but got " + componentTypeList.size()
                            + " for component type list " + componentTypeList
            );
        }
        return componentTypeList.stream()
                .map(oi -> componentTypeMap.get(oi.getId()))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new ControlStructureParserException(
                        "Component type not found for id " + componentTypeList.getFirst().getId()
                        )
                );
    }

    private static String getShortInfo(SysMLV2Element e) {
        String idStatement = "Id: " + e.getId();
        String nameStatement = Optional.of(e)
                .filter(NamedElement.class::isInstance)
                .map(NamedElement.class::cast)
                .map(NamedElement::getName)
                .map(name -> ", name: " + name)
                .orElse("");
        String classStatement = ", class: " + e.getClass().getSimpleName();

        return idStatement+nameStatement+classStatement;
    }

    private static String getInfo(SysMLV2Element e, Map<String, SysMLV2Element> elementsMap) {
        String ownerStatement = Optional.of(e)
                                        .filter(OwnedElement.class::isInstance)
                                        .map(OwnedElement.class::cast)
                                        .map(OwnedElement::getOwner)
                                        .map(el -> elementsMap.get(el.getId()))
                                        .map(SysMLV2JsonParser::getShortInfo)
                                        .map(st -> ", owned by (" + st + ")")
                                        .orElse("");

        return getShortInfo(e) + ownerStatement;
    }

    @Override
    public boolean supports(File input) {
        return input.exists() && input.getName().endsWith(SUPPORTED_FILE_EXTENSION);
    }
}
