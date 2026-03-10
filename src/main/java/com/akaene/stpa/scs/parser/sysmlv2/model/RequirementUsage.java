package com.akaene.stpa.scs.parser.sysmlv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A RequirementUsage captures a concrete requirement instance in a model, used to apply
 * a RequirementDefinition within a specific system context.
 *
 * <p>SysML v2 RDF Definition:
 * A RequirementUsage is a Usage whose definition is a RequirementDefinition. It represents
 * a specific requirement within the context of a system or part of a system, with its own
 * subject and stakeholders, and potentially its own constraints and verifications.</p>
 */
public class RequirementUsage extends SysMLV2Element implements NamedElement, OwnedElement {

    private String name;
    private String qualifiedName;
    private ObjectIdentity owningNamespace;
    private ObjectIdentity owner;
    private Boolean isComposite;
    private ObjectIdentity declaration;
    private String reqId;
    private List<String> text = List.of();
    private List<ObjectIdentity> ownedElement = List.of();
    private List<ObjectIdentity> ownedMember = List.of();

    public RequirementUsage() {
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

    public ObjectIdentity getDeclaration() {
        return declaration;
    }

    public void setDeclaration(ObjectIdentity declaration) {
        this.declaration = declaration;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text != null ? new ArrayList<>(text) : List.of();
    }

    @Override
    public String toString() {
        return "RequirementUsage{" +
                "id='" + getId() + '\'' +
                ", type='" + getElementType() + '\'' +
                ", identifier='" + getIdentifier() + '\'' +
                ", name='" + name + '\'' +
                ", qualifiedName='" + qualifiedName + '\'' +
                ", owningNamespace=" + owningNamespace +
                ", owner=" + owner +
                ", isComposite=" + isComposite +
                ", declaration=" + declaration +
                ", reqId='" + reqId + '\'' +
                ", text=" + text +
                ", ownedElement=" + ownedElement.size() + " items" +
                ", ownedMember=" + ownedMember.size() + " items" +
                '}';
    }
}
