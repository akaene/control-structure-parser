package com.akaene.stpa.scs.parser.sysmlv2.model;

/**
 * Interface for elements that can have an owner.
 */
public interface OwnedElement {
    ObjectIdentity getOwner();
}
