package com.akaene.stpa.scs.parser.sysmlv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A FlowConnectionDefinition specifies a flow-typed connection (between end Usages), used
 * to define what can flow between parts and to type FlowConnectionUsages.
 *
 * <p>SysML v2 RDF Definition:
 * A FlowConnectionDefinition is a ConnectionDefinition that is also a FlowDefinition.
 * The end Features of a FlowConnectionDefinition must be Usages. It defines flows
 * between Usages, representing the transfer of values or items between connected parts.</p>
 */
public class FlowConnectionDefinition extends SysMLV2Element implements NamedElement, OwnedElement {

    private String name;
    private String qualifiedName;
    private ObjectIdentity owningNamespace;
    private ObjectIdentity owner;
    private List<ObjectIdentity> ownedElement = List.of();
    private List<ObjectIdentity> ownedMember = List.of();
    private List<ObjectIdentity> ownedEndFeature = List.of();
    private List<ObjectIdentity> ownedFeature = List.of();
    private List<ObjectIdentity> directedFeature = List.of();
    private List<ObjectIdentity> feature = List.of();
    private List<ObjectIdentity> ownedUsage = List.of();
    private List<ObjectIdentity> ownedReference = List.of();
    private List<ObjectIdentity> target = List.of();
    private List<ObjectIdentity> relatedElement = List.of();
    private List<ObjectIdentity> associationEnd = List.of();
    private List<ObjectIdentity> targetType = List.of();
    private List<ObjectIdentity> relatedType = List.of();
    private List<ObjectIdentity> connectionEnd = List.of();
    private List<ObjectIdentity> parameter = List.of();

    public FlowConnectionDefinition() {
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

    public List<ObjectIdentity> getOwnedEndFeature() {
        return ownedEndFeature;
    }

    public void setOwnedEndFeature(List<ObjectIdentity> ownedEndFeature) {
        this.ownedEndFeature = ownedEndFeature != null ? new ArrayList<>(ownedEndFeature) : List.of();
    }

    public List<ObjectIdentity> getOwnedFeature() {
        return ownedFeature;
    }

    public void setOwnedFeature(List<ObjectIdentity> ownedFeature) {
        this.ownedFeature = ownedFeature != null ? new ArrayList<>(ownedFeature) : List.of();
    }

    public List<ObjectIdentity> getDirectedFeature() {
        return directedFeature;
    }

    public void setDirectedFeature(List<ObjectIdentity> directedFeature) {
        this.directedFeature = directedFeature != null ? new ArrayList<>(directedFeature) : List.of();
    }

    public List<ObjectIdentity> getFeature() {
        return feature;
    }

    public void setFeature(List<ObjectIdentity> feature) {
        this.feature = feature != null ? new ArrayList<>(feature) : List.of();
    }

    public List<ObjectIdentity> getOwnedUsage() {
        return ownedUsage;
    }

    public void setOwnedUsage(List<ObjectIdentity> ownedUsage) {
        this.ownedUsage = ownedUsage != null ? new ArrayList<>(ownedUsage) : List.of();
    }

    public List<ObjectIdentity> getOwnedReference() {
        return ownedReference;
    }

    public void setOwnedReference(List<ObjectIdentity> ownedReference) {
        this.ownedReference = ownedReference != null ? new ArrayList<>(ownedReference) : List.of();
    }

    public List<ObjectIdentity> getTarget() {
        return target;
    }

    public void setTarget(List<ObjectIdentity> target) {
        this.target = target != null ? new ArrayList<>(target) : List.of();
    }

    public List<ObjectIdentity> getRelatedElement() {
        return relatedElement;
    }

    public void setRelatedElement(List<ObjectIdentity> relatedElement) {
        this.relatedElement = relatedElement != null ? new ArrayList<>(relatedElement) : List.of();
    }

    public List<ObjectIdentity> getAssociationEnd() {
        return associationEnd;
    }

    public void setAssociationEnd(List<ObjectIdentity> associationEnd) {
        this.associationEnd = associationEnd != null ? new ArrayList<>(associationEnd) : List.of();
    }

    public List<ObjectIdentity> getTargetType() {
        return targetType;
    }

    public void setTargetType(List<ObjectIdentity> targetType) {
        this.targetType = targetType != null ? new ArrayList<>(targetType) : List.of();
    }

    public List<ObjectIdentity> getRelatedType() {
        return relatedType;
    }

    public void setRelatedType(List<ObjectIdentity> relatedType) {
        this.relatedType = relatedType != null ? new ArrayList<>(relatedType) : List.of();
    }

    public List<ObjectIdentity> getConnectionEnd() {
        return connectionEnd;
    }

    public void setConnectionEnd(List<ObjectIdentity> connectionEnd) {
        this.connectionEnd = connectionEnd != null ? new ArrayList<>(connectionEnd) : List.of();
    }

    public List<ObjectIdentity> getParameter() {
        return parameter;
    }

    public void setParameter(List<ObjectIdentity> parameter) {
        this.parameter = parameter != null ? new ArrayList<>(parameter) : List.of();
    }

    @Override
    public String toString() {
        return "FlowConnectionDefinition{" +
                "id='" + getId() + '\'' +
                ", type='" + getElementType() + '\'' +
                ", identifier='" + getIdentifier() + '\'' +
                ", name='" + name + '\'' +
                ", qualifiedName='" + qualifiedName + '\'' +
                ", owningNamespace=" + owningNamespace +
                ", owner=" + owner +
                ", ownedElement=" + ownedElement.size() + " items" +
                ", ownedMember=" + ownedMember.size() + " items" +
                ", ownedEndFeature=" + ownedEndFeature.size() + " items" +
                ", ownedFeature=" + ownedFeature.size() + " items" +
                ", directedFeature=" + directedFeature.size() + " items" +
                ", feature=" + feature.size() + " items" +
                ", ownedUsage=" + ownedUsage.size() + " items" +
                ", ownedReference=" + ownedReference.size() + " items" +
                ", target=" + target.size() + " items" +
                ", relatedElement=" + relatedElement.size() + " items" +
                ", associationEnd=" + associationEnd.size() + " items" +
                ", targetType=" + targetType.size() + " items" +
                ", relatedType=" + relatedType.size() + " items" +
                ", connectionEnd=" + connectionEnd.size() + " items" +
                ", parameter=" + parameter.size() + " items" +
                '}';
    }
}
