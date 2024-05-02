package com.akaene.stpa.scs.parser.sysml;

import com.akaene.stpa.scs.model.Model;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.*;

class SysMLXMIParserTest {

    private final SysMLXMIParser sut = new SysMLXMIParser();

    @Test
    void parseAsResourceLoadsFileAsEmfResource() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model/model.xmi").toURI());
        final Resource res = sut.parseAsResource(input);
        assertNotNull(res);
        assertInstanceOf(org.eclipse.uml2.uml.Model.class, res.getContents().getFirst());
    }

    @Test
    void parseExtractsClassesFromInputModel() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model/model.xmi").toURI());
        final Model result = sut.parse(input);
        assertNotNull(result);
        assertTrue(result.getClass("System").isPresent());
        assertTrue(result.getClass("Controlled Process").isPresent());
        assertTrue(result.getClass("Controller").isPresent());
    }

    @Test
    void parseExtractsPartOfCompositionAssociationsFromInputModel() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("complex-model/model.xmi").toURI());
        final Model result = sut.parse(input);
        assertNotNull(result);
        assertFalse(result.getAssociations().isEmpty());
    }

    @Disabled
    @Test
    void parseZipArchiveExtractsArchiveIntoTemporaryFolderAndThenParsesModelFileInIt() throws Exception {
        try (final ZipFile input = new ZipFile(new File(getClass().getClassLoader().getResource("simple-model.zip").toURI()))) {
            final com.akaene.stpa.scs.model.Model result = sut.parse(input);
            assertNotNull(result);
            assertFalse(result.getClasses().isEmpty());
        }
    }
}
