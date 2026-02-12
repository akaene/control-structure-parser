package com.akaene.stpa.scs.parser.sysmlv2;

import com.akaene.stpa.scs.model.Model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SysMLV2JsonParserTest {

    private final SysMLV2JsonParser sut = new SysMLV2JsonParser();

    @ParameterizedTest
    @MethodSource("sampleFileTypes")
    void supportsReturnsTrueForSupportedFiles(String file, boolean supports) throws Exception {
        final File input = getInput(file);
        assertEquals(supports, sut.supports(input));
    }

    static Stream<Arguments> sampleFileTypes() {
        return Stream.of(
                Arguments.of("sysmlv2/complex-model.json", true),
                Arguments.of("sysmlv2/model-with-multiple-packages.json", true),
                Arguments.of("sysml/simple-model/model.xmi", false),
                Arguments.of("graphml/simple-model.graphml", false)
        );
    }

    private static File getInput(String path) throws URISyntaxException {
        assert SysMLV2JsonParserTest.class.getClassLoader().getResource(path) != null;
        return new File(SysMLV2JsonParserTest.class.getClassLoader().getResource(path).toURI());
    }

    @Test
    void parseComplexModel() throws Exception {
        final File input = getInput("sysmlv2/complex-model.json");
        final Model result = sut.parse(input);
        assertNotNull(result);
    }

    @Test
    void parseWithMultiplePackagesReturnModelWithNameOfRootPackage() throws URISyntaxException {
        final File input = getInput("sysmlv2/model-with-multiple-packages.json");
        final Model result = sut.parse(input);
        assertEquals("my-root-package", result.getName());
    }

//    @Test
//    void parseValidatesJsonArrayStructure() throws Exception {
//        final File input = getInput("sysmlv2/complex-model.json");
//        final Model result = sut.parse(input);
//        assertNotNull(result);
//    }
//
//    @Test
//    void parseThrowsExceptionForNonArrayJson() throws Exception {
//        final File input = getInput("sysmlv2/complex-model.json");
//        assertNotNull(input);
//        assertTrue(input.exists());
//        assertTrue(sut.supports(input));
//    }
//
//    @Test
//    void parseHandlesElectricalSystemJsonFile() throws Exception {
//        final File input = getInput("sysmlv2/complex-model.json");
//        assertNotNull(input);
//        assertTrue(input.exists());
//        assertTrue(sut.supports(input));
//
//        final Model result = sut.parse(input);
//        assertNotNull(result);
//    }
//
//    @Test
//    void parseValidatesFileExists() throws Exception {
//        final File nonExistentFile = new File("non-existent.json");
//        assertFalse(sut.supports(nonExistentFile));
//    }
//
//    @Test
//    void parseValidatesJsonExtension() throws Exception {
//        final File input = getInput("sysmlv2/complex-model.json");
//        assertTrue(input.getName().endsWith(".json"));
//        assertTrue(sut.supports(input));
//    }
}
