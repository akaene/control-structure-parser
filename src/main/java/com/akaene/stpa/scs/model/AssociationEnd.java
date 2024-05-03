package com.akaene.stpa.scs.model;

import com.akaene.stpa.scs.util.CardinalityUtils;

public record AssociationEnd(ComponentType type, AggregationType aggregation, String role, Integer min, Integer max) {

    @Override
    public String toString() {
        return type.getName() + "." + role + "[" + CardinalityUtils.toString(
                min) + ".." + CardinalityUtils.toString(max) + ']';
    }
}
