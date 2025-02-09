package com.akaene.stpa.scs.parser.graphml;

import com.akaene.stpa.scs.exception.ControlStructureParserException;
import com.akaene.stpa.scs.model.Connector;
import com.akaene.stpa.scs.model.Model;
import com.akaene.stpa.scs.model.Stereotype;
import com.akaene.stpa.scs.parser.ControlStructureParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        final SourceType sourceType = resolveSourceType(document);
        readNodes(document, sourceType).forEach(n -> state.nodes.put(n.id(), n));
        readConnectors(state, document, sourceType).forEach(state.result::addConnector);
        return state.result;
    }

    private Document readDocument(File file) {
        try {
            return Jsoup.parse(file, StandardCharsets.UTF_8.name(), "", Parser.xmlParser());
        } catch (IOException e) {
            throw new ControlStructureParserException("Unable to parse file " + file.getName(), e);
        }
    }

    private SourceType resolveSourceType(Document document) {
        final Element jsonNode = document.select("graph y|Json").first();
        if (jsonNode == null) {
            return SourceType.DESKTOP;
        }
        return jsonNode.text().contains("yed-live") ? SourceType.ONLINE : SourceType.DESKTOP;
    }

    private List<Node> readNodes(Document document, SourceType sourceType) {
        final GraphMLReader reader = switch (sourceType) {
            case DESKTOP -> new DesktopGraphMLReader();
            case ONLINE -> new OnlineGraphMLReader();
        };
        final List<Node> nodes = reader.readNodes(document);
        LOG.trace("Found {} nodes.", nodes.size());
        return nodes;
    }

    private List<Connector> readConnectors(ParsingState state, Document document, SourceType sourceType) {
        final GraphMLReader reader = switch (sourceType) {
            case DESKTOP -> new DesktopGraphMLReader();
            case ONLINE -> new OnlineGraphMLReader();
        };
        final List<Connector> connectors = reader.readConnectors(state, document);
        LOG.trace("Found {} connectors.", connectors.size());
        return connectors;
    }

    @Override
    public boolean supports(File input) {
        return input.exists() && input.getName().endsWith(FILE_EXTENSION);
    }

    private enum SourceType {
        DESKTOP, ONLINE
    }

    enum EdgeStereotype {

        ControlAction("ControlAction"),
        Feedback("Feedback"),
        AdditionalInfo("AdditionalControlInformation");

        private final Stereotype stereotype;

        EdgeStereotype(String stereotype) {
            this.stereotype = new Stereotype(stereotype);
        }

        public Stereotype getStereotype() {
            return stereotype;
        }
    }

    static class ParsingState {

        final Model result = new Model();

        final Map<String, Node> nodes = new HashMap<>();

        private ParsingState() {
            for (EdgeStereotype es : EdgeStereotype.values()) {
                result.addStereotype(es.getStereotype());
            }
        }
    }
}
