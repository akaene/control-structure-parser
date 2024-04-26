package com.akaene.stpa.scs.model;

public record Stereotype (String name) {

    @Override
    public String toString() {
        return "<<" + name + ">>";
    }
}
