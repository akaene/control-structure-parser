package com.akaene.stpa.scs.parser.graphml;

import com.akaene.stpa.scs.model.Connector;
import com.akaene.stpa.scs.model.ConnectorEnd;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Reads elements (nodes, edges) from a GraphML document.
 */
abstract class GraphMLReader {

    private static final Logger LOG = LoggerFactory.getLogger(GraphMLReader.class);

    /**
     * Reads nodes from the specified GraphML document.
     *
     * @param document Document to read nodes from
     * @return List of nodes read from the document
     */
    abstract List<Node> readNodes(Document document);

    /**
     * Reads connectors from the specified GraphML document.
     *
     * @param state    Current state of the model, contains nodes read from the document
     * @param document Document to read connectors from
     * @return List of connectors read from the document
     */
    List<Connector> readConnectors(GraphMLParser.ParsingState state, Document document) {
        final Elements edges = document.select("edge");
        final List<Connector> connectors = new ArrayList<>();
        edges.forEach(e -> {
            final String id = e.id();
            final Node source = state.nodes.get(e.attr("source"));
            final Node target = state.nodes.get(e.attr("target"));
            if (source == null || target == null) {
                LOG.error("Edge {} is missing resolved source or target node.", id);
                return;
            }
            final Optional<GraphMLParser.EdgeStereotype> stereotype = edgeToStereotype(e);
            for (String labelItem : getLabelItems(e)) {
                final Connector connector = new Connector(labelItem, id,
                                                          new ConnectorEnd(source.component(), null, null, null),
                                                          new ConnectorEnd(target.component(), null, null, null));
                stereotype.ifPresent(s -> connector.addStereotype(s.getStereotype()));
                connectors.add(connector);
            }
        });
        return connectors;
    }

    abstract List<String> getLabelItems(Element edge);

    abstract Optional<GraphMLParser.EdgeStereotype> edgeToStereotype(Element edge);
}
