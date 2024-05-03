package com.akaene.stpa.scs.model;

import com.akaene.stpa.scs.util.CardinalityUtils;

public record ConnectorEnd(Component type, String role, Integer min, Integer max, boolean navigable) {

    @Override
    public String toString() {
        return type + (role != null ? "." + role : "") + (navigable ? "<" : "") + "[" + CardinalityUtils.toString(
                min) + ".." + CardinalityUtils.toString(max) + ']';
    }
}
