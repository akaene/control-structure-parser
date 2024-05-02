package com.akaene.stpa.scs.parser.sysml;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Model;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SysMLXMIParserTest {

    private final SysMLXMIParser sut = new SysMLXMIParser();

    @Test
    void parseAsResourceLoadsFileAsEmfResource() throws Exception {
        final File simpleProject = new File(getClass().getClassLoader().getResource("simple_project.xmi").toURI());
        final Resource res = sut.parseAsResource(simpleProject);
        assertNotNull(res);
        assertInstanceOf(Model.class, res.getContents().getFirst());
    }

}
