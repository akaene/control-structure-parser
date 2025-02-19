package com.akaene.stpa.scs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Connector implements Stereotyped {

    private final String name;

    private final String qualifiedName;

    private final ConnectorEnd source;

    private final ConnectorEnd target;

    private final List<Stereotype> stereotypes = new ArrayList<>();

    public Connector(String name, String qualifiedName, ConnectorEnd source, ConnectorEnd target) {
        this.name = name;
        this.qualifiedName = qualifiedName;
        this.source = source;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public ConnectorEnd getSource() {
        return source;
    }

    public ConnectorEnd getTarget() {
        return target;
    }

    @Override
    public List<Stereotype> getStereotypes() {
        return stereotypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Connector connector)) {
            return false;
        }
        return Objects.equals(getName(), connector.getName()) && Objects.equals(getQualifiedName(),
                                                                                connector.getQualifiedName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getQualifiedName());
    }

    @Override
    public String toString() {
        final String stereos = stereotypes.stream().map(Object::toString).collect(
                Collectors.joining(","));
        return "Connector{" + source + " - " + name + (!stereos.isBlank() ? " " + stereos : "") + " -> " + target + "}";
    }
}
