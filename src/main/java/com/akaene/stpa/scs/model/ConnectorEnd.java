package com.akaene.stpa.scs.model;

import com.akaene.stpa.scs.util.CardinalityUtils;

public record ConnectorEnd(Connectable type, String property, Integer min, Integer max, boolean navigable) {

    @Override
    public String toString() {
        return type.name() + "." + property + (navigable ? "<" : "") + "[" + CardinalityUtils.toString(
                min) + ".." + CardinalityUtils.toString(max) + ']';
    }
}
