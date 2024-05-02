package com.akaene.stpa.scs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Association {

    private final String name;


    private final AssociationEnd source;

    private final AssociationEnd target;

    private final List<Stereotype> stereotypes = new ArrayList<>();

    public Association(String name, AssociationEnd source, AssociationEnd target) {
        this.source = source;
        this.target = target;
        this.name = Objects.requireNonNullElseGet(name, () -> source.type()
                                                                    .getName() + "." + source.role() + "-" + target.type()
                                                                                                                   .getName() + "." + target.role());
    }

    public String getName() {
        return name;
    }

    public AssociationEnd getSource() {
        return source;
    }

    public AssociationEnd getTarget() {
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
        return "Association{" + source + " -> " + name + " -> " + target + '}';
    }
}
