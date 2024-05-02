package com.akaene.stpa.scs.model;

import java.util.ArrayList;
import java.util.List;

public class ComponentType {

    private final String name;

    private final List<Stereotype> stereotypes = new ArrayList<>();

    private final List<Component> parts = new ArrayList<>();

    public ComponentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Component> getParts() {
        return parts;
    }

    public void addPart(Component part) {
        parts.add(part);
    }

    public List<Stereotype> getStereotypes() {
        return stereotypes;
    }

    public void addStereotype(Stereotype stereotype) {
        stereotypes.add(stereotype);
    }

    @Override
    public String toString() {
        return "ComponentType{" + name + " " + stereotypes + '}';
    }
}
