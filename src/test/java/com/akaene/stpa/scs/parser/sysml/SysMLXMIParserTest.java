package com.akaene.stpa.scs.parser.sysml;

import com.akaene.stpa.scs.exception.ControlStructureParserException;
import com.akaene.stpa.scs.model.Connector;
import com.akaene.stpa.scs.model.Model;
import com.akaene.stpa.scs.model.Stereotype;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SysMLXMIParserTest {

    private final SysMLXMIParser sut = new SysMLXMIParser();

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
        assertFalse(result.getClasses().isEmpty());
        assertFalse(result.getConnectors().isEmpty());
        assertFalse(result.getAssociations().isEmpty());
    }

    @Test
    void parseExtractsConnectors() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model/model.xmi").toURI());
        final Model result = sut.parse(input);
        assertNotNull(result);
        assertEquals(2, result.getConnectors().size());
        verifyConnectorsInSimpleModel(result);
    }

    public static void verifyConnectorsInSimpleModel(Model result) {
        final Optional<Connector> controlAction = result.getConnectors().stream()
                                                        .filter(c -> c.getName().equals("change altitude")).findAny();
        assertTrue(controlAction.isPresent());
        assertTrue(controlAction.get().getStereotypes().stream().anyMatch(s -> s.name().equals("ControlAction")));
        assertEquals("FlightCrew", controlAction.get().getSource().type().name());
        assertEquals(result.getClass("Controller").get(), controlAction.get().getSource().type().getType());
        assertEquals("Flight", controlAction.get().getTarget().type().name());
        assertEquals(result.getClass("Controlled Process").get(), controlAction.get().getTarget().type().getType());
        final Optional<Connector> feedback = result.getConnectors().stream().filter(c -> c.getName().equals("altitude"))
                                                   .findAny();
        assertTrue(feedback.isPresent());
        assertTrue(feedback.get().getStereotypes().stream().anyMatch(s -> s.name().equals("Feedback")));
        assertEquals("Flight", feedback.get().getSource().type().name());
        assertEquals(result.getClass("Controlled Process").get(), feedback.get().getSource().type().getType());
        assertEquals("FlightCrew", feedback.get().getTarget().type().name());
        assertEquals(result.getClass("Controller").get(), feedback.get().getTarget().type().getType());
    }

    @Test
    void parseExtractsConnectorsWithStereotypes() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model/model.xmi").toURI());

        final Model result = sut.parse(input);
        assertNotNull(result);
        assertEquals(2, result.getConnectors().size());
        final Optional<Connector> controlAction = result.getConnectors().stream()
                                                        .filter(c -> c.getName().equals("change altitude")).findAny();
        assertTrue(controlAction.isPresent());
        final Optional<Stereotype> caStereotype = result.getStereotype("ControlAction");
        assertTrue(caStereotype.isPresent());
        assertThat(controlAction.get().getStereotypes(), hasItem(caStereotype.get()));
        final Optional<Connector> feedback = result.getConnectors().stream().filter(c -> c.getName().equals("altitude"))
                                                   .findAny();
        assertTrue(feedback.isPresent());
        final Optional<Stereotype> feedbackStereotype = result.getStereotype("Feedback");
        assertTrue(feedbackStereotype.isPresent());
        assertThat(feedback.get().getStereotypes(), hasItem(feedbackStereotype.get()));
    }

    @Test
    void parseZipArchiveExtractsArchiveIntoTemporaryFolderAndThenParsesModelFileInIt() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model.zip").toURI());
        final com.akaene.stpa.scs.model.Model result = sut.parse(input);
        assertNotNull(result);
        assertFalse(result.getClasses().isEmpty());
    }

    @Test
    void parseSupportsUmlExtension() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model-uml/model.uml").toURI());
        final Model result = sut.parse(input);
        assertNotNull(result);
        assertTrue(result.getClass("System").isPresent());
        assertTrue(result.getClass("ControlledProcess").isPresent());
        assertTrue(result.getClass("Controller").isPresent());
    }

    @Test
    void parseExtractsQualifiedNamesOfElements() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model/model.xmi").toURI());
        final Model result = sut.parse(input);
        result.getClasses().forEach(c -> assertThat(c.getQualifiedName(), not(blankOrNullString())));
        result.getConnectors().forEach(c -> assertThat(c.getQualifiedName(), not(blankOrNullString())));
        result.getAssociations().stream().filter(a -> a.getName() != null)
              .forEach(a -> assertThat(a.getQualifiedName(), not(blankOrNullString())));
    }

    @Test
    void parseZipSupportsModelWithUmlExtension() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model-uml.zip").toURI());
        final com.akaene.stpa.scs.model.Model result = sut.parse(input);
        assertNotNull(result);
        assertFalse(result.getClasses().isEmpty());
    }

    @Test
    void parseThrowsControlStructureParserExceptionWhenEmfIsUnableToParseFile() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("invalid-model.xml").toURI());
        assertThrows(ControlStructureParserException.class, () -> sut.parse(input));
    }

    @Test
    void parseRecognizesProfileAndModelFilesAndUsesOnlyAvailableModelFileForParsing() throws Exception {
        final File input = new File(
                getClass().getClassLoader().getResource("simple_project_different_filename.zip").toURI());
        final com.akaene.stpa.scs.model.Model result = sut.parse(input);
        assertNotNull(result);
        assertFalse(result.getClasses().isEmpty());
    }

    @Test
    void parseExtractsStereotypesFromFileProducedEnterpriseArchitect() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model_EA.xml").toURI());
        final Model result = sut.parse(input);
        assertTrue(result.getStereotype("ControlAction").isPresent());
        assertTrue(result.getStereotype("Feedback").isPresent());
    }

    @Test
    void parseHandlesSimpleXmlFileProducedByEnterpriseArchitect() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model_EA.xml").toURI());
        final Model result = sut.parse(input);
        assertNotNull(result);
        verifyConnectorsInSimpleModel(result);
    }

    @Test
    void parseHandlesComplexXmlFileProducedByEnterpriseArchitect() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("complex-model_EA.xml").toURI());
        final Model result = sut.parse(input);
        assertNotNull(result);
        assertFalse(result.getClasses().isEmpty());
        assertFalse(result.getConnectors().isEmpty());
        assertFalse(result.getAssociations().isEmpty());
    }

    @ParameterizedTest
    @MethodSource("sampleFileTypes")
    void supportsReturnsTrueForSupportedFiles(String file, boolean supports) throws Exception {
        final File input = new File(getClass().getClassLoader().getResource(file).toURI());
        assertEquals(supports, sut.supports(input));
    }

    static Stream<Arguments> sampleFileTypes() {
        return Stream.of(
                Arguments.of("simple-model/model.xmi", true),
                Arguments.of("simple-model.zip", true),
                Arguments.of("simple-model-uml/model.uml", true),
                Arguments.of("complex-model_EA.xml", "true"),
                Arguments.of("simple-model.graphml", false)
        );
    }
}
