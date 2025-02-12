package com.akaene.stpa.scs.parser.graphml;

import com.akaene.stpa.scs.model.Component;
import com.akaene.stpa.scs.model.DiagramNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Reads nodes from GraphML files produced by yEd.
 */
class DesktopGraphMLReader extends GraphMLReader {

    private static final Logger LOG = LoggerFactory.getLogger(DesktopGraphMLReader.class);

    public List<Node> readNodes(Document document) {
        final Elements nodeElements = document.select("node");
        final Map<String, Node> nodeMap = new HashMap<>(nodeElements.size());
        nodeElements.stream().filter(n -> !n.select("y|Shape[type=\"rectangle\"]").isEmpty())
                           .map(n -> {
                               final String id = n.id();
                               final String label = selectElementLabels(n);
                               if (label.isBlank()) {
                                   LOG.warn("Node with id {} has no label.", id);
                                   return null;
                               }
                               final Component component = new Component(label, id, null);
                               component.setDiagramNode(extractDiagramNode(n));
                               return new Node(id, label, component);
                           }).filter(Objects::nonNull)
                .forEach(n -> nodeMap.put(n.id(), n));
        connectChildrenToParents(nodeMap);
        return new ArrayList<>(nodeMap.values());
    }

    private static String selectElementLabels(Element elem) {
        if (!elem.select("graph").isEmpty()) {
            final Elements labels = elem.select("y|NodeLabel");
            if (!labels.isEmpty()) {
                return labels.getFirst().text().trim();
            }
            return "";
        } else {
            return elem.select("y|NodeLabel").stream()
                       .map(e -> e.text().trim())
                       .filter(s -> !s.isEmpty())
                       .collect(Collectors.joining(" "))
                       .replace('\n', ' ').trim();
        }
    }

    private DiagramNode extractDiagramNode(Element node) {
        final Element geometry = node.select("y|Geometry").first();
        if (geometry == null) {
            return null;
        }
        try {
            final float x = Float.parseFloat(geometry.attr("x"));
            final float y = Float.parseFloat(geometry.attr("y"));
            final float width = Float.parseFloat(geometry.attr("width"));
            final float height = Float.parseFloat(geometry.attr("height"));
            return new DiagramNode(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
        } catch (NumberFormatException e) {
            LOG.error("Unable to extract geometry of node {}.", geometry, e);
            return null;
        }
    }

    @Override
    List<String> getLabelItems(Element edge) {
        final Elements labels = edge.select("y|EdgeLabel");
        String label = labels.stream().map(l -> l.wholeText().trim()).filter(s -> !s.isEmpty())
                             .collect(Collectors.joining("\n"));
        return label.isBlank() ? List.of() : List.of(label.split("\n"));
    }

    @Override
    Optional<GraphMLParser.EdgeStereotype> edgeToStereotype(Element edge) {
        final String lineType = edge.select("y|LineStyle").attr("type");
        switch (lineType) {
            case "line":
                return Optional.of(GraphMLParser.EdgeStereotype.ControlAction);
            case "dashed":
                return Optional.of(GraphMLParser.EdgeStereotype.Feedback);
            case "dotted":
                return Optional.of(GraphMLParser.EdgeStereotype.AdditionalInfo);
        }
        LOG.debug("Edge {} is of no matching stereotyped type.", edge);
        return Optional.empty();
    }
}
