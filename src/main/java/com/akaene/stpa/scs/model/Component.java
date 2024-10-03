package com.akaene.stpa.scs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Component implements Connectable, Stereotyped {

    private final String name;

    private final String qualifiedName;

    private final ComponentType type;

    private final List<Stereotype> stereotypes = new ArrayList<>();

    private DiagramNode diagramNode;

    public Component(String name, String qualifiedName, ComponentType type) {
        this.name = name;
        this.qualifiedName = qualifiedName;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String name() {
        return name;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public ComponentType getType() {
        return type;
    }

    @Override
    public List<Stereotype> getStereotypes() {
        return stereotypes;
    }

    public Optional<DiagramNode> getDiagramNode() {
        return Optional.ofNullable(diagramNode);
    }

    public void setDiagramNode(DiagramNode diagramNode) {
        this.diagramNode = diagramNode;
    }

    @Override
    public String toString() {
        return name + " : " + (type != null ? type.getName() : "");
    }
}
