package com.akaene.stpa.scs.parser.sysmlv2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * An ObjectIdentity represents a reference to a SysML v2 Element with a unique identifier.
 * In SysML v2, every Element is uniquely identified relative to all other Elements.
 *
 * <p>JSON representation is an object with a single attribute - {@literal @id}.</p>
 */
public class ObjectIdentity {
    @JsonProperty("@id")
    private String id;

    public ObjectIdentity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectIdentity that = (ObjectIdentity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
