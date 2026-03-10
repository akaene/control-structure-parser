package com.akaene.stpa.scs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Connector implements Stereotyped, InterchangeIdentifiable {

    private final String name;

    private final String qualifiedName;

    private final ConnectorEnd source;

    private final ConnectorEnd target;

    private final String identifier;

    private final List<Stereotype> stereotypes = new ArrayList<>();

    public Connector(String name, String qualifiedName, ConnectorEnd source, ConnectorEnd target) {
        this(name, qualifiedName, source, target, null);
    }
    
    public Connector(String name, String qualifiedName, ConnectorEnd source, ConnectorEnd target, String identifier) {
        this.name = name;
        this.qualifiedName = qualifiedName;
        this.source = source;
        this.target = target;
        this.identifier = identifier;
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
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public List<Stereotype> getStereotypes() {
        return stereotypes;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Connector connector)) {
            return false;
        }
        return Objects.equals(name, connector.name) && Objects.equals(qualifiedName, connector.qualifiedName) &&
                Objects.equals(source, connector.source) && Objects.equals(target, connector.target) &&
                Objects.equals(stereotypes, connector.stereotypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, qualifiedName, source, target, stereotypes);
    }

    @Override
    public String toString() {
        final String stereos = stereotypes.stream().map(Object::toString).collect(
                Collectors.joining(","));
        return "Connector{" + source + " - " + name + (!stereos.isBlank() ? " " + stereos : "") + " -> " + target + "}";
    }
}
