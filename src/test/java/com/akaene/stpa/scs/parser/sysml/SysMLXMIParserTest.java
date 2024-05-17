package com.akaene.stpa.scs.parser.sysml;

import com.akaene.stpa.scs.exception.ControlStructureParserException;
import com.akaene.stpa.scs.model.Connector;
import com.akaene.stpa.scs.model.Model;
import com.akaene.stpa.scs.model.Stereotype;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Optional;
import java.util.zip.ZipFile;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        final Optional<Connector> controlAction = result.getConnectors().stream()
                                                        .filter(c -> c.getName().equals("change altitude")).findAny();
        assertTrue(controlAction.isPresent());
        assertEquals("FlightCrew", controlAction.get().getSource().type().name());
        assertEquals(result.getClass("Controller").get(), controlAction.get().getSource().type().getType());
        assertEquals("Flight", controlAction.get().getTarget().type().name());
        assertEquals(result.getClass("Controlled Process").get(), controlAction.get().getTarget().type().getType());
        final Optional<Connector> feedback = result.getConnectors().stream().filter(c -> c.getName().equals("altitude"))
                                                   .findAny();
        assertTrue(feedback.isPresent());
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
        try (final ZipFile input = new ZipFile(
                new File(getClass().getClassLoader().getResource("simple-model.zip").toURI()))) {
            final com.akaene.stpa.scs.model.Model result = sut.parse(input);
            assertNotNull(result);
            assertFalse(result.getClasses().isEmpty());
        }
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
        result.getAssociations().stream().filter(a -> a.getName() != null).forEach(a -> assertThat(a.getQualifiedName(), not(blankOrNullString())));
    }

    @Test
    void parseZipSupportsModelWithUmlExtension() throws Exception {
        try (final ZipFile input = new ZipFile(
                new File(getClass().getClassLoader().getResource("simple-model-uml.zip").toURI()))) {
            final com.akaene.stpa.scs.model.Model result = sut.parse(input);
            assertNotNull(result);
            assertFalse(result.getClasses().isEmpty());
        }
    }

    @Test
    void parseThrowsControlStructureParserExceptionWhenEmfIsUnableToParseFile() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("invalid-model.xml").toURI());
        assertThrows(ControlStructureParserException.class, () -> sut.parse(input));
    }

    @Test
    void parseRecognizesProfileAndModelFilesAndUsesOnlyAvailableModelFileForParsing() throws Exception {
        try (final ZipFile input = new ZipFile(
                new File(getClass().getClassLoader().getResource("simple_project_different_filename.zip").toURI()))) {
            final com.akaene.stpa.scs.model.Model result = sut.parse(input);
            assertNotNull(result);
            assertFalse(result.getClasses().isEmpty());
        }
    }
}
