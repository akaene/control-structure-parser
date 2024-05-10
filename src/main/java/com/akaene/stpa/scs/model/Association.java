package com.akaene.stpa.scs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Association implements Stereotyped {

    private final String name;

    private final String qualifiedName;

    private final AssociationEnd source;

    private final AssociationEnd target;

    private final List<Stereotype> stereotypes = new ArrayList<>();

    public Association(String name, String qualifiedName, AssociationEnd source, AssociationEnd target) {
        this.source = source;
        this.target = target;
        this.name = Objects.requireNonNullElseGet(name, () -> source.type()
                                                                    .getName() + "::" + source.role() + "-" + target.type()
                                                                                                                    .getName() + "::" + target.role());
        this.qualifiedName = qualifiedName;
    }

    public String getName() {
        return name;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public AssociationEnd getSource() {
        return source;
    }

    public AssociationEnd getTarget() {
        return target;
    }

    @Override
    public List<Stereotype> getStereotypes() {
        return stereotypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Association that)) return false;
        return Objects.equals(getName(), that.getName())
                && (Objects.equals(getSource(), that.getSource()) && Objects.equals(getTarget(), that.getTarget()) ||
                Objects.equals(getSource(), that.getTarget()) && Objects.equals(
                        getTarget(), that.getSource())) && Objects.equals(getStereotypes(), that.getStereotypes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSource(), getTarget(), getStereotypes());
    }

    @Override
    public String toString() {
        return "Association{" + source + " -> " + name + " -> " + target + '}';
    }
}
