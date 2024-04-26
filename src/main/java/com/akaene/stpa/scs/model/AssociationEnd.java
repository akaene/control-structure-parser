package com.akaene.stpa.scs.model;

public record AssociationEnd(ComponentType type, String property, Cardinality min, Cardinality max, boolean navigable) {

    @Override
    public String toString() {
        return type.getName() + "." + property + (navigable ? "<" : "") + "[" + min + ".." + max + ']';
    }
}
