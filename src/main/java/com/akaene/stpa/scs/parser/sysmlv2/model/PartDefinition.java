package com.akaene.stpa.scs.parser.sysmlv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A PartDefinition defines a reusable part type (its structure and features), used to
 * type PartUsages in a system model.
 *
 * <p>SysML v2 RDF Definition:
 * A PartDefinition is a Definition and a Structure of things that may themselves be
 * systems or parts of systems. All features of a PartDefinition must be composite
 * by default (isComposite = true), unless they are explicitly set to be referential
 * (isComposite = false).</p>
 */
public class PartDefinition extends SysMLV2Element implements NamedElement {

    private String name;
    private String qualifiedName;
    private ObjectIdentity owner;
    private ObjectIdentity owningNamespace;
    private final List<Object> eAnnotations = new ArrayList<>();
    private final List<ObjectIdentity> ownedElement = new ArrayList<>();
    private final List<ObjectIdentity> ownedMember = new ArrayList<>();

    public PartDefinition() {
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

    @Override
    public String toString() {
        return "PartDefinition{" +
                "id='" + getId() + '\'' +
                ", type='" + getElementType() + '\'' +
                ", identifier='" + getIdentifier() + '\'' +
                ", name='" + name + '\'' +
                ", qualifiedName='" + qualifiedName + '\'' +
                ", owner=" + owner +
                ", owningNamespace=" + owningNamespace +
                ", eAnnotations=" + eAnnotations.size() + " items" +
                ", ownedElement=" + ownedElement.size() + " items" +
                ", ownedMember=" + ownedMember.size() + " items" +
                '}';
    }
}
