package com.akaene.stpa.scs.parser.sysml;

import com.akaene.stpa.scs.model.AggregationType;
import com.akaene.stpa.scs.model.Association;
import com.akaene.stpa.scs.model.AssociationEnd;
import com.akaene.stpa.scs.model.Component;
import com.akaene.stpa.scs.model.ComponentType;
import com.akaene.stpa.scs.model.Connector;
import com.akaene.stpa.scs.model.ConnectorEnd;
import com.akaene.stpa.scs.model.Model;
import com.akaene.stpa.scs.model.Stereotype;
import com.akaene.stpa.scs.parser.ControlStructureParser;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.resource.XMI2UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipFile;

/**
 * Parses control structure from a SysML XMI artifact.
 */
public class SysMLXMIParser implements ControlStructureParser {

    private static final Logger LOG = LoggerFactory.getLogger(SysMLXMIParser.class);

    static {
        UMLResourcesUtil.initGlobalRegistries();
    }

    @Override
    public Model parse(ZipFile input) {
        // TODO
        return null;
    }

    @Override
    public Model parse(File input) {
        final Resource xmi = parseAsResource(input);
        final ParsingState state = new ParsingState();
        extractStereotypes(xmi, state);
        extractClasses(xmi, state);
        extractConnectors(xmi, state);
        LOG.debug("Parsed model:\n{}", state.result);
        return state.result;
    }

    public Resource parseAsResource(File input) {
        LOG.debug("Parsing XMI file '{}'.", input);
        ResourceSet set = new ResourceSetImpl();
        set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
        final URI uri = URI.createFileURI(input.getAbsolutePath());
        return set.getResource(uri, true);
    }

    private void extractStereotypes(Resource xmi, ParsingState state) {
        xmi.getContents().stream()
           .filter(o -> o instanceof DynamicEObjectImpl)
           .map(DynamicEObjectImpl.class::cast)
           .forEach(stereotype -> {
               final Stereotype result = new Stereotype(stereotype.eClass().getName());
               state.result.addStereotype(result);
               state.stereotypes.put(stereotype, result);
           });
    }

    private void extractClasses(Resource xmi, ParsingState state) {
        assert xmi.getContents().getFirst() instanceof org.eclipse.uml2.uml.Model;
        final org.eclipse.uml2.uml.Model xmiModel = (org.eclipse.uml2.uml.Model) xmi.getContents().getFirst();
        xmiModel.allOwnedElements().stream()
                .filter(o -> o instanceof Class)
                .map(o -> (Class) o)
                .map(cls -> {
                    final ComponentType ct = new ComponentType(cls.getName());
                    getElementStereotypes(cls, state).forEach(ct::addStereotype);
                    return ct;
                })
                .forEach(state.result::addClass);
        resolveSupertypesAndParts(xmiModel, state);
    }

    private void resolveSupertypesAndParts(org.eclipse.uml2.uml.Model xmiModel, ParsingState state) {
        xmiModel.allOwnedElements().stream()
                .filter(o -> o instanceof Class)
                .map(Class.class::cast)
                .forEach(cls -> {
                    final Optional<ComponentType> target = state.result.getClass(cls.getName());
                    assert target.isPresent();
                    getSuperTypes(cls, state).forEach(ct -> target.get().addSuperType(ct));
                    final Collection<Association> partOf = extractPartOfAssociations(cls, state);
                    partOf.forEach(association -> {
                        state.result.addAssociation(association);
                        target.get().addPart(association);
                    });
                });
    }

    private Collection<Stereotype> getElementStereotypes(Object element, ParsingState state) {
        return state.stereotypes.entrySet().stream().filter(e -> hasStereotype(e.getKey(), element))
                                .map(Map.Entry::getValue).toList();
    }

    private static boolean hasStereotype(DynamicEObjectImpl stereotypeElem, Object element) {
        int max = stereotypeElem.eClass().getEAllStructuralFeatures().size();

        for (int i = 0; i < max; i++) {
            Object p = stereotypeElem.dynamicGet(i);
            if (p == element) {
                return true;
            }
        }
        return false;
    }

    private Collection<ComponentType> getSuperTypes(Class cls, ParsingState state) {
        return cls.getSuperClasses().stream().map(supertype -> state.result.getClass(supertype.getName())).flatMap(
                Optional::stream).toList();
    }

    private Collection<Association> extractPartOfAssociations(Class cls, ParsingState state) {
        return cls.getParts().stream().map(part -> {
            final AssociationEnd target = propertyToAssociationEnd(part, state);
            final AssociationEnd source;
            if (part.getOtherEnd() != null) {
                source = propertyToAssociationEnd(part.getOtherEnd(), state);
            } else {
                final Optional<ComponentType> sourceType = state.result.getClass(cls.getName());
                assert sourceType.isPresent();
                source = new AssociationEnd(sourceType.get(), AggregationType.ASSOCIATION,
                                            null, 0, null, false);
            }
            final Association association = new Association(
                    part.getAssociation() != null ? part.getAssociation().getName() : null, source, target);
            getElementStereotypes(part.getAssociation(), state).forEach(association::addStereotype);
            return association;
        }).toList();
    }

    private ComponentType propertyType(Property property, ParsingState state) {
        return Optional.ofNullable(property.getType()).flatMap(ct -> state.result.getClass(ct.getName()))
                       .orElse(ComponentType.UNSPECIFIED);
    }

    private AssociationEnd propertyToAssociationEnd(Property property, ParsingState state) {
        final ComponentType targetType = propertyType(property, state);
        return new AssociationEnd(targetType, aggregationType(property.getAggregation()),
                                  property.getName(), property.getLower(), property.getUpper(),
                                  property.isNavigable());
    }

    private static AggregationType aggregationType(AggregationKind emfAggregation) {
        return switch (emfAggregation) {
            case NONE_LITERAL -> AggregationType.ASSOCIATION;
            case SHARED_LITERAL -> AggregationType.AGGREGATION;
            case COMPOSITE_LITERAL -> AggregationType.COMPOSITION;
        };
    }

    private void extractConnectors(Resource xmi, ParsingState state) {
        assert xmi.getContents().getFirst() instanceof org.eclipse.uml2.uml.Model;
        final org.eclipse.uml2.uml.Model xmiModel = (org.eclipse.uml2.uml.Model) xmi.getContents().getFirst();
        final List<org.eclipse.uml2.uml.Connector> connectors = xmiModel.allOwnedElements().stream()
                                                                        .filter(o -> o instanceof org.eclipse.uml2.uml.Connector)
                                                                        .map(org.eclipse.uml2.uml.Connector.class::cast)
                                                                        .toList();
        connectors.stream().map(c -> {
            assert c.getEnds().size() == 2;

            final ConnectorEnd source = connectorEnd(c.getEnds().getFirst(), state);
            final ConnectorEnd target = connectorEnd(c.getEnds().get(1), state);
            final Connector connector = new Connector(c.getName(), source, target);
            getElementStereotypes(c.getEnds().getFirst(), state).forEach(connector::addStereotype);
            getElementStereotypes(c.getEnds().get(1), state).forEach(connector::addStereotype);
            return connector;
        }).forEach(state.result::addConnector);
    }

    private ConnectorEnd connectorEnd(org.eclipse.uml2.uml.ConnectorEnd umlConnectorEnd, ParsingState state) {
        final ConnectableElement connected = umlConnectorEnd.getRole() != null ? umlConnectorEnd.getRole() :
                                             umlConnectorEnd.getPartWithPort();
        final Optional<ComponentType> type = state.result.getClass(connected.getType().getName());
        final Component comp = new Component(connected.getName(), type.orElse(ComponentType.UNSPECIFIED));
        return new ConnectorEnd(comp, null, umlConnectorEnd.getLower(), umlConnectorEnd.getUpper(),
                                umlConnectorEnd.getDefiningEnd() != null && umlConnectorEnd.getDefiningEnd()
                                                                                           .isNavigable());
    }

    private static final class ParsingState {

        private final Model result = new Model();

        private final Map<DynamicEObjectImpl, Stereotype> stereotypes = new HashMap<>();
    }
}
