package com.akaene.stpa.scs.model;

import com.akaene.stpa.scs.util.CardinalityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ComponentType implements Stereotyped {

    public static ComponentType UNSPECIFIED = new ComponentType("", "");

    private final String name;

    private final String qualifiedName;

    private final List<ComponentType> superTypes = new ArrayList<>();

    private final List<Stereotype> stereotypes = new ArrayList<>();

    private final List<Association> attributes = new ArrayList<>();

    public ComponentType(String name, String qualifiedName) {
        this.name = name;
        this.qualifiedName = qualifiedName;
    }

    public String getName() {
        return name;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public List<ComponentType> getSuperTypes() {
        return superTypes;
    }

    public void addSuperType(ComponentType superType) {
        superTypes.add(superType);
    }

    public List<Association> getAttributes() {
        return attributes;
    }

    public void addAttribute(Association att) {
        attributes.add(att);
    }

    @Override
    public List<Stereotype> getStereotypes() {
        return stereotypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComponentType that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getSuperTypes(),
                                                                           that.getSuperTypes()) && Objects.equals(
                getStereotypes(), that.getStereotypes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSuperTypes(), getStereotypes());
    }

    @Override
    public String toString() {
        final String atts = attributes.stream().map(a -> "\t" + a.getTarget().type().getName() + " " + a.getTarget()
                                                                                                        .role() + " [" + CardinalityUtils.toString(
                a.getTarget().min()) + ".." + CardinalityUtils.toString(a.getTarget().max()) + "];").collect(
                Collectors.joining("\n"));
        return name + (!stereotypes.isEmpty() ? " " + stereotypes : "") + (!atts.isBlank() ? " {\n" + atts +"\n}" : " { }");
    }
}
