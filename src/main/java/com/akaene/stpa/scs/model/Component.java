package com.akaene.stpa.scs.model;

import java.util.ArrayList;
import java.util.List;

public class Component implements Connectable {

    private final String name;

    private final ComponentType type;

    private final List<Stereotype> stereotypes = new ArrayList<>();

    public Component(String name, ComponentType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String name() {
        return name;
    }

    public ComponentType getType() {
        return type;
    }

    public List<Stereotype> getStereotypes() {
        return stereotypes;
    }

    public void addStereotype(Stereotype stereotype) {
        stereotypes.add(stereotype);
    }

    @Override
    public String toString() {
        return name + " : " + type.getName();
    }
}
