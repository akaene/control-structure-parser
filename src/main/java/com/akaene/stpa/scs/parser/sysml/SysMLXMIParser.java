package com.akaene.stpa.scs.parser.sysml;

import com.akaene.stpa.scs.model.ComponentType;
import com.akaene.stpa.scs.model.Model;
import com.akaene.stpa.scs.model.Stereotype;
import com.akaene.stpa.scs.parser.ControlStructureParser;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.resource.XMI2UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
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
        // TODO Parts
        xmiModel.allOwnedElements().stream()
                .filter(o -> o instanceof Class)
                .map(o -> (Class) o)
                .map(cls -> {
                    final ComponentType ct = new ComponentType(cls.getName());
                    state.stereotypes.entrySet().stream().filter(e -> hasStereotype(e.getKey(), cls))
                                     .forEach(e -> ct.addStereotype(e.getValue()));
                    return ct;
                })
                .forEach(state.result::addClass);
    }

    private boolean hasStereotype(DynamicEObjectImpl stereotypeElem, Object element) {
        int max = stereotypeElem.eClass().getEAllStructuralFeatures().size();

        for (int i = 0; i < max; i++) {
            Object p = stereotypeElem.dynamicGet(i);
            if (p == element) {
                return true;
            }
        }
        return false;
    }

    private static final class ParsingState {

        private final Model result = new Model();

        private final Map<DynamicEObjectImpl, Stereotype> stereotypes = new HashMap<>();
    }
}
