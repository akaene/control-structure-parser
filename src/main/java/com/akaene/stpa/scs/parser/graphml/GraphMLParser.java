package com.akaene.stpa.scs.parser.graphml;

import com.akaene.stpa.scs.exception.ControlStructureParserException;
import com.akaene.stpa.scs.model.Component;
import com.akaene.stpa.scs.model.Connector;
import com.akaene.stpa.scs.model.ConnectorEnd;
import com.akaene.stpa.scs.model.Model;
import com.akaene.stpa.scs.model.Stereotype;
import com.akaene.stpa.scs.parser.ControlStructureParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Control structure parser for GraphML files (produced by yEd).
 * <p>
 * It expects:
 * <ul>
 *     <li>Components to be represented by shape of type {@literal rectangle}</li>
 *     <li>Control actions to be represented by edges of type {@literal line}</li>
 *     <li>Feedback to be represented by edges of type {@literal dashed}</li>
 *     <li>Additional control information to be represented by edges of type {@literal dotted}</li>
 * </ul>
 */
public class GraphMLParser implements ControlStructureParser {

    private static final Logger LOG = LoggerFactory.getLogger(GraphMLParser.class);

    public static final String FILE_EXTENSION = ".graphml";

    @Override
    public Model parse(File input) {
        LOG.debug("Parsing input using {}.", getClass().getSimpleName());
        final Document document = readDocument(input);
        final ParsingState state = new ParsingState();
        readNodes(document).forEach(n -> state.nodes.put(n.id(), n));
        readConnectors(state, document);
        return state.result;
    }

    private Document readDocument(File file) {
        try {
            return Jsoup.parse(file, StandardCharsets.UTF_8.name(), "", Parser.xmlParser());
        } catch (IOException e) {
            throw new ControlStructureParserException("Unable to parse file " + file.getName(), e);
        }
    }

    private List<Node> readNodes(Document document) {
        final Elements nodeElements = document.select("node");
        final List<Node> nodes = nodeElements.stream().filter(n -> !n.select("y|Shape[type=\"rectangle\"]").isEmpty())
                                             .map(n -> {
                                                 final String id = n.id();
                                                 final String label = n.select("y|NodeLabel").stream()
                                                                       .map(e -> e.text().trim())
                                                                       .collect(Collectors.joining(" "))
                                                                       .replace('\n', ' ');
                                                 return new Node(id, label, new Component(label, id, null));
                                             }).toList();
        LOG.trace("Found {} nodes.", nodes.size());
        return nodes;
    }

    private void readConnectors(ParsingState state, Document document) {
        final Elements edges = document.select("edge");
        edges.forEach(e -> {
            final String id = e.id();
            final Node source = state.nodes.get(e.attr("source"));
            final Node target = state.nodes.get(e.attr("target"));
            if (source == null || target == null) {
                LOG.error("Edge {} is missing resolved source or target node.", id);
                return;
            }
            final Elements labels = e.select("y|EdgeLabel");
            final String label = labels.stream().map(l -> l.wholeText().trim()).collect(Collectors.joining("\n"));
            final Optional<EdgeStereotype> stereotype = edgeToStereotype(e);
            final String[] items = label.split("\n");
            for (String labelItem : items) {
                final Connector connector = new Connector(labelItem, id,
                                                          new ConnectorEnd(source.component(), null, null, null),
                                                          new ConnectorEnd(target.component(), null, null, null));
                stereotype.ifPresent(s -> connector.addStereotype(s.getStereotype()));
                state.result.addConnector(connector);
            }
        });
    }

    private Optional<EdgeStereotype> edgeToStereotype(Element edge) {
        final String lineType = edge.select("y|LineStyle").attr("type");
        for (EdgeStereotype stereotype : EdgeStereotype.values()) {
            if (stereotype.getEdgeType().equals(lineType)) {
                return Optional.of(stereotype);
            }
        }
        LOG.debug("Edge {} is of no matching stereotyped type.", edge);
        return Optional.empty();
    }

    @Override
    public boolean supports(File input) {
        return input.exists() && input.getName().endsWith(FILE_EXTENSION);
    }

    private record Node(String id, String label, Component component) {
    }

    private enum EdgeStereotype {

        ControlAction("line", "ControlAction"), Feedback("dashed", "Feedback"),
        AdditionalInfo("dotted", "AdditionalControlInformation");

        private final String edgeType;
        private final Stereotype stereotype;

        EdgeStereotype(String edgeType, String stereotype) {
            this.edgeType = edgeType;
            this.stereotype = new Stereotype(stereotype);
        }

        public String getEdgeType() {
            return edgeType;
        }

        public Stereotype getStereotype() {
            return stereotype;
        }
    }

    private static class ParsingState {

        private final Model result = new Model();

        private final Map<String, Node> nodes = new HashMap<>();

        private ParsingState() {
            for (EdgeStereotype es : EdgeStereotype.values()) {
                result.addStereotype(es.getStereotype());
            }
        }
    }
}
