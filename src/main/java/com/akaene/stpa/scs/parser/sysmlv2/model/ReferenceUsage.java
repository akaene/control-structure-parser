package com.akaene.stpa.scs.parser.sysmlv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A ReferenceUsage models a reference-valued feature, used when a part points to
 * (rather than contains) another element.
 *
 * <p>SysML v2 RDF Definition:
 * A ReferenceUsage is a Usage whose type is a Classifier. Nominally, if the type is a
 * ReferenceDefinition, a ReferenceUsage is a usage of a ReferenceDefinition to represent
 * a reference to something. However, other kinds of kernel Classifiers are also allowed,
 * to permit use of Classifiers from the Kernel Model Libraries. A ReferenceUsage itself
 * as well as all its nested features must be referential (non-composite).</p>
 */
public class ReferenceUsage extends SysMLV2Element implements NamedElement, OwnedElement {

    private String name;
    private String qualifiedName;
    private ObjectIdentity owningNamespace;
    private ObjectIdentity owner;
    private Boolean isComposite;
    private Boolean isEnd;
    private ObjectIdentity declaration;
    private List<ObjectIdentity> ownedElement = List.of();
    private List<ObjectIdentity> ownedMember = List.of();

    public ReferenceUsage() {
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

    public ObjectIdentity getOwningNamespace() {
        return owningNamespace;
    }

    public void setOwningNamespace(ObjectIdentity owningNamespace) {
        this.owningNamespace = owningNamespace;
    }

    public ObjectIdentity getOwner() {
        return owner;
    }

    public void setOwner(ObjectIdentity owner) {
        this.owner = owner;
    }

    public List<ObjectIdentity> getOwnedElement() {
        return ownedElement;
    }

    public void setOwnedElement(List<ObjectIdentity> ownedElement) {
        this.ownedElement = ownedElement != null ? new ArrayList<>(ownedElement) : List.of();
    }

    public List<ObjectIdentity> getOwnedMember() {
        return ownedMember;
    }

    public void setOwnedMember(List<ObjectIdentity> ownedMember) {
        this.ownedMember = ownedMember != null ? new ArrayList<>(ownedMember) : List.of();
    }

    public Boolean getIsComposite() {
        return isComposite;
    }

    public void setIsComposite(Boolean isComposite) {
        this.isComposite = isComposite;
    }

    public Boolean getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Boolean isEnd) {
        this.isEnd = isEnd;
    }

    public ObjectIdentity getDeclaration() {
        return declaration;
    }

    public void setDeclaration(ObjectIdentity declaration) {
        this.declaration = declaration;
    }

    @Override
    public String toString() {
        return "ReferenceUsage{" +
                "id='" + getId() + '\'' +
                ", type='" + getElementType() + '\'' +
                ", identifier='" + getIdentifier() + '\'' +
                ", name='" + name + '\'' +
                ", qualifiedName='" + qualifiedName + '\'' +
                ", owningNamespace=" + owningNamespace +
                ", owner=" + owner +
                ", isComposite=" + isComposite +
                ", isEnd=" + isEnd +
                ", declaration=" + declaration +
                ", ownedElement=" + ownedElement.size() + " items" +
                ", ownedMember=" + ownedMember.size() + " items" +
                '}';
    }
}
