package com.akaene.stpa.scs.model;

public record ConnectorEnd(Component type, String role, Integer min, Integer max) {

    @Override
    public String toString() {
        return type + (role != null ? "." + role : "");
    }
}
