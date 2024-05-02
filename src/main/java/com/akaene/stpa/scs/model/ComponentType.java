package com.akaene.stpa.scs.model;

import java.util.ArrayList;
import java.util.List;

public class ComponentType {

    public static ComponentType UNSPECIFIED = new ComponentType("");

    private final String name;

    private final List<ComponentType> superTypes = new ArrayList<>();

    private final List<Stereotype> stereotypes = new ArrayList<>();

    private final List<Association> parts = new ArrayList<>();

    public ComponentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<ComponentType> getSuperTypes() {
        return superTypes;
    }

    public void addSuperType(ComponentType superType) {
        superTypes.add(superType);
    }

    public List<Association> getParts() {
        return parts;
    }

    public void addPart(Association part) {
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
        return name + (!stereotypes.isEmpty() ? " " + stereotypes : "");
    }
}
