package com.akaene.stpa.scs.model;

import java.util.ArrayList;
import java.util.List;

public class Association {

    private final String name;

    private final AssociationType type;

    private final AssociationEnd source;

    private final AssociationEnd target;

    private final List<Stereotype> stereotypes = new ArrayList<>();

    public Association(String name, AssociationType type, AssociationEnd source, AssociationEnd target) {
        this.name = name;
        this.type = type;
        this.source = source;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public AssociationType getType() {
        return type;
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
