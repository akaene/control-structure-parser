package com.akaene.stpa.scs.model;

public record ConnectorEnd(Connectable type, String property, Cardinality min, Cardinality max, boolean navigable) {

    @Override
    public String toString() {
        return type.name() + "." + property + (navigable ? "<" : "") + "[" + min + ".." + max + ']';
    }
}
