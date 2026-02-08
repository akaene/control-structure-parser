package com.akaene.stpa.scs.parser.sysmlv2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * A Package groups model elements into a namespace, used to structure models and avoid
 * naming conflicts.
 *
 * <p>SysML v2 RDF Definition:
 * A Package is a Namespace that is also an Element. A Package can own other Elements
 * and can be owned by other Packages, forming a hierarchical organization of model
 * elements. Packages are used to organize model content and provide namespaces for
 * avoiding naming conflicts.</p>
 */
public class Package extends SysMLV2Element implements NamedElement {

    private String name;
    private String qualifiedName;
    private ObjectIdentity owner;
    private ObjectIdentity owningNamespace;
    @JsonProperty("eAnnotations")
    private final List<Object> eAnnotations = new ArrayList<>();
    private final List<ObjectIdentity> ownedElement = new ArrayList<>();
    private final List<ObjectIdentity> ownedMember = new ArrayList<>();

    public Package() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public ObjectIdentity getOwner() {
        return owner;
    }

    public void setOwner(ObjectIdentity owner) {
        this.owner = owner;
    }

    public ObjectIdentity getOwningNamespace() {
        return owningNamespace;
    }

    public void setOwningNamespace(ObjectIdentity owningNamespace) {
        this.owningNamespace = owningNamespace;
    }

    public List<Object> getEAnnotations() {
        return eAnnotations;
    }

    public void setEAnnotations(List<Object> eAnnotations) {
        this.eAnnotations.clear();
        if (eAnnotations != null) {
            this.eAnnotations.addAll(eAnnotations);
        }
    }

    public List<ObjectIdentity> getOwnedElement() {
        return ownedElement;
    }

    public void setOwnedElement(List<ObjectIdentity> ownedElement) {
        this.ownedElement.clear();
        if (ownedElement != null) {
            this.ownedElement.addAll(ownedElement);
        }
    }

    public List<ObjectIdentity> getOwnedMember() {
        return ownedMember;
    }

    public void setOwnedMember(List<ObjectIdentity> ownedMember) {
        this.ownedMember.clear();
        if (ownedMember != null) {
            this.ownedMember.addAll(ownedMember);
        }
    }

    @Override
    public String toString() {
        return "Package{" +
                "id='" + getId() + '\'' +
                ", type='" + getElementType() + '\'' +
                ", identifier='" + getIdentifier() + '\'' +
                ", name='" + name + '\'' +
                ", qualifiedName='" + qualifiedName + '\'' +
                ", owner=" + (owner != null ? owner.getId() : "null") +
                ", owningNamespace=" + (owningNamespace != null ? owningNamespace.getId() : "null") +
                ", eAnnotations=" + eAnnotations.size() + " items" +
                ", ownedElement=" + ownedElement.size() + " items" +
                ", ownedMember=" + ownedMember.size() + " items" +
                '}';
    }
}
