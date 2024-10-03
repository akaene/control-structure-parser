package com.akaene.stpa.scs.parser.graphml;

import com.akaene.stpa.scs.model.Component;
import com.akaene.stpa.scs.model.Connector;
import com.akaene.stpa.scs.model.DiagramNode;
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
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphMLParserTest {

    private final GraphMLParser sut = new GraphMLParser();

    @ParameterizedTest
    @MethodSource("sampleFileTypes")
    void supportsReturnsTrueForSupportedFiles(String file, boolean supports) throws Exception {
        final File input = new File(getClass().getClassLoader().getResource(file).toURI());
        assertEquals(supports, sut.supports(input));
    }

    static Stream<Arguments> sampleFileTypes() {
        return Stream.of(
                Arguments.of("simple-model/model.xmi", false),
                Arguments.of("simple-model.zip", false),
                Arguments.of("simple-model.graphml", true)
        );
    }

    @Test
    void parserHandlesSimpleGraphMLFile() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model.graphml").toURI());
        final Model result = sut.parse(input);
        assertNotNull(result);
        verifyConnector(result, "change altitude", "FlightCrew", "Flight", new Stereotype("ControlAction"));
        verifyConnector(result, "altitude", "Flight", "FlightCrew", new Stereotype("Feedback"));
    }

    private static void verifyConnector(Model result, String label, String from, String to, Stereotype stereotype) {
        final Optional<Connector> connector = result.getConnectors().stream()
                                                    .filter(c -> c.getName().equals(label)).findAny();
        assertTrue(connector.isPresent());
        assertThat(connector.get().getStereotypes(), hasItem(stereotype));
        assertEquals(from, connector.get().getSource().type().name());
        assertEquals(to, connector.get().getTarget().type().name());
    }

    @Test
    void parseHandlesGraphMLFileWithMultipleActionsAndFeedbacksOnOneEdge() throws Exception {
        final File input = new File(
                getClass().getClassLoader().getResource("model-compound-actions-feedback.graphml").toURI());
        final Model result = sut.parse(input);
        assertNotNull(result);
        verifyConnector(result, "change altitude", "FlightCrew", "Flight", new Stereotype("ControlAction"));
        verifyConnector(result, "accelerate", "FlightCrew", "Flight", new Stereotype("ControlAction"));
        verifyConnector(result, "decelerate", "FlightCrew", "Flight", new Stereotype("ControlAction"));
        verifyConnector(result, "altitude", "Flight", "FlightCrew", new Stereotype("Feedback"));
        verifyConnector(result, "airspeed", "Flight", "FlightCrew", new Stereotype("Feedback"));
    }

    @Test
    void parseHandlesAdditionalControlInfoConnectors() throws Exception {
        final File input = new File(
                getClass().getClassLoader().getResource("model-compound-actions-feedback.graphml").toURI());
        final Model result = sut.parse(input);
        assertNotNull(result);
        verifyConnector(result, "weather and traffic info", "ANS", "FlightCrew",
                        new Stereotype("AdditionalControlInformation"));
    }

    @Test
    void parseExtractsNodePositionAndSizeInformationFromFile() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model.graphml").toURI());
        final Model result = sut.parse(input);
        final Optional<Connector> connector = result.getConnectors().stream()
                                                    .filter(c -> c.getName().equals("change altitude")).findAny();
        assertTrue(connector.isPresent());
        final Component flightCrew = connector.get().getSource().type();
        assertTrue(flightCrew.getDiagramNode().isPresent());
        final DiagramNode flightCrewNode = flightCrew.getDiagramNode().get();
        assertEquals(593, flightCrewNode.getX());
        assertEquals(206, flightCrewNode.getY());
        assertEquals(139, flightCrewNode.getWidth());
        assertEquals(47, flightCrewNode.getHeight());

        final Component flight = connector.get().getTarget().type();
        assertTrue(flight.getDiagramNode().isPresent());
        final DiagramNode flightNode = flight.getDiagramNode().get();
        assertEquals(593, flightNode.getX());
        assertEquals(391, flightNode.getY());
        assertEquals(139, flightNode.getWidth());
        assertEquals(47, flightNode.getHeight());
    }
}
