package com.akaene.stpa.scs.model;

import java.util.List;

public interface Stereotyped {

    List<Stereotype> getStereotypes();

    default void addStereotype(Stereotype stereotype) {
        if (!getStereotypes().contains(stereotype)) {
            getStereotypes().add(stereotype);
        }
    }

    default boolean hasStereotype(Stereotype stereotype) {
        return getStereotypes().contains(stereotype);
    }
}
