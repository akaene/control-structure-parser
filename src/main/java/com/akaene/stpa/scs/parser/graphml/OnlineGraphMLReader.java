package com.akaene.stpa.scs.parser.graphml;

import com.akaene.stpa.scs.model.Component;
import com.akaene.stpa.scs.model.DiagramNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Reads nodes from GraphML files produced by yEd Live (online version of yEd).
 */
public class OnlineGraphMLReader extends GraphMLReader {

    private static final Logger LOG = LoggerFactory.getLogger(OnlineGraphMLReader.class);

    @Override
    List<Node> readNodes(Document document) {
        final Elements nodeElements = document.select("node");
        return nodeElements.stream()
                           .filter(el -> !el.select("yjs|ShapeNodeStyle").not("[shape]").isEmpty())
                           .map(n -> {
                               final String id = n.id();
                               final String label = n.select("y|Label").stream()
                                                     .map(e -> e.attr("TExt").trim())
                                                     .filter(s -> !s.isEmpty())
                                                     .collect(Collectors.joining(" "))
                                                     .replace('\n', ' ');
                               final Component component = new Component(label, id, null);
                               component.setDiagramNode(extractDiagramNode(n));
                               return new Node(id, label, component);
                           }).toList();
    }

    private DiagramNode extractDiagramNode(Element node) {
        final Element rectangle = node.select("y|RectD").first();
        if (rectangle == null) {
            return null;
        }
        try {
            final float x = Float.parseFloat(rectangle.attr("X"));
            final float y = Float.parseFloat(rectangle.attr("X"));
            final float width = Float.parseFloat(rectangle.attr("Width"));
            final float height = Float.parseFloat(rectangle.attr("Height"));
            return new DiagramNode(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
        } catch (NumberFormatException e) {
            LOG.error("Unable to extract rectangle of node {}.", rectangle, e);
            return null;
        }
    }

    @Override
    List<String> getLabelItems(Element edge) {
        final Elements labels = edge.select("y|Label");
        final String label = labels.stream().map(l -> l.attr("Text").trim()).filter(s -> !s.isEmpty())
                                   .collect(Collectors.joining("\n"));
        return List.of(label.split("\n"));
    }

    @Override
    Optional<GraphMLParser.EdgeStereotype> edgeToStereotype(Element edge) {
        final Elements lineStyle = edge.select("yjs|Stroke");
        if (lineStyle.isEmpty()) {
            return Optional.of(GraphMLParser.EdgeStereotype.ControlAction);
        }
        final String style = lineStyle.attr("dashStyle");
        switch (style) {
            case "Dash":
                return Optional.of(GraphMLParser.EdgeStereotype.Feedback);
            case "Dot":
                return Optional.of(GraphMLParser.EdgeStereotype.AdditionalInfo);
        }
        LOG.debug("Edge {} is of no matching stereotyped type.", edge);
        return Optional.empty();
    }
}
