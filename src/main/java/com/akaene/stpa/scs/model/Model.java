package com.akaene.stpa.scs.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Model {

    private final Map<String, ComponentType> classes = new HashMap<>();

    private final Map<String, Association> associations = new HashMap<>();

    private final Map<String, Stereotype> stereotypes = new HashMap<>();

    private final Collection<Connector> connectors = new HashSet<>();

    private final Collection<Component> components = new HashSet<>();

    private String name;

    public Collection<ComponentType> getClasses() {
        return classes.values();
    }

    public Model addClass(ComponentType cls) {
        classes.put(cls.getName(), cls);
        return this;
    }

    public Optional<ComponentType> getClass(String name) {
        return Optional.ofNullable(classes.get(name));
    }

    public Collection<Association> getAssociations() {
        return associations.values();
    }

    public Model addAssociation(Association association) {
        associations.put(association.getName(), association);
        return this;
    }

    public Optional<Association> getAssociation(String name) {
        return Optional.ofNullable(associations.get(name));
    }

    public Collection<Connector> getConnectors() {
        return Collections.unmodifiableCollection(connectors);
    }

    public Model addConnector(Connector connector) {
        connectors.add(connector);
        components.add(connector.getSource().type());
        components.add(connector.getTarget().type());
        return this;
    }

    public Collection<Component> getComponents() {
        return Collections.unmodifiableCollection(components);
    }

    public Model addComponent(Component component) {
        components.add(component);
        return this;
    }

    public Collection<Stereotype> getStereotypes() {
        return stereotypes.values();
    }

    public Model addStereotype(Stereotype stereotype) {
        stereotypes.put(stereotype.name(), stereotype);
        return this;
    }

    public Optional<Stereotype> getStereotype(String name) {
        return Optional.ofNullable(stereotypes.get(name));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "\nClasses (" + classes.size() + "):\n" + classes.values().stream().map(Objects::toString).collect(
                Collectors.joining("\n")) + "\n\n" +
                "Associations (" + associations.size() + "):\n" + associations.values().stream().map(Object::toString)
                                                                              .collect(
                                                                                      Collectors.joining("\n")) + "\n\n" +
                "Connectors (" + connectors.size() + "):\n" + connectors.stream().map(Objects::toString).collect(
                Collectors.joining("\n"));
    }
}
