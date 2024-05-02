package com.akaene.stpa.scs.parser.sysml;

import com.akaene.stpa.scs.model.Model;
import com.akaene.stpa.scs.parser.ControlStructureParser;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.resource.XMI2UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

/**
 * Parses control structure from a SysML XMI artifact.
 */
public class SysMLXMIParser implements ControlStructureParser {

    private static final Logger LOG = LoggerFactory.getLogger(SysMLXMIParser.class);

    static{
        UMLResourcesUtil.initGlobalRegistries();
    }


    @Override
    public Model parse(InputStream input) {
        return null;
    }

    @Override
    public Model parse(File input) {
        return null;
    }

    public Resource parseAsResource(File input) {
        LOG.debug("Parsing XMI file '{}'.", input);
        ResourceSet set = new ResourceSetImpl();
        set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
        final URI uri = URI.createFileURI(input.getAbsolutePath());
        return set.getResource(uri, true);
    }
}
