package com.akaene.stpa.scs.model;

import java.util.ArrayList;
import java.util.List;

public class Connector {

    private final String name;

    private final ConnectorEnd source;

    private final ConnectorEnd target;

    private final List<Stereotype> stereotypes = new ArrayList<>();

    public Connector(String name, ConnectorEnd source, ConnectorEnd target) {
        this.name = name;
        this.source = source;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public ConnectorEnd getSource() {
        return source;
    }

    public ConnectorEnd getTarget() {
        return target;
    }

    public List<Stereotype> getStereotypes() {
        return stereotypes;
    }

    public void addStereotype(Stereotype stereotype) {
        stereotypes.add(stereotype);
    }

    @Override
    public String toString() {
        return "Connector{" + source + " -> " + name + " -> " + target + "}";
    }
}
