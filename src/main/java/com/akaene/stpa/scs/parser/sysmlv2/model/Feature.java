package com.akaene.stpa.scs.parser.sysmlv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A Feature defines a typed relationship (property/end) between things, used to model
 * what values relate model elements.
 *
 * <p>SysML v2 RDF Definition:
 * A Feature is a Type that classifies relations between multiple things (in the universe).
 * The domain of the relation is the intersection of the featuringTypes of the Feature.
 * (The domain of a Feature with no featuringTypes is implicitly the most general Type
 * Base::Anything from the Kernel Semantic Library.) The co-domain of the relation is
 * the intersection of the types of the Feature.</p>
 */
public class Feature extends SysMLV2Element {

    private String name;
    private String qualifiedName;
    private ObjectIdentity owner;
    private ObjectIdentity owningRelationship;
    private ObjectIdentity owningNamespace;
    private List<Object> ownedRelationship = List.of();
    private List<ObjectIdentity> ownedElement = List.of();
    private List<ObjectIdentity> ownedSpecialization = List.of();
    private Boolean isEnd;
    private List<ObjectIdentity> ownedSubsetting = List.of();
    private ObjectIdentity declaration;
    private List<ObjectIdentity> ownedReferenceSubsetting = List.of();
    private List<ObjectIdentity> referencedFeature = List.of();
    private List<ObjectIdentity> directedFeature = List.of();
    private List<ObjectIdentity> feature = List.of();
    private List<ObjectIdentity> inheritedFeature = List.of();

    public Feature() {
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

    public ObjectIdentity getOwningRelationship() {
        return owningRelationship;
    }

    public void setOwningRelationship(ObjectIdentity owningRelationship) {
        this.owningRelationship = owningRelationship;
    }

    public List<Object> getOwnedRelationship() {
        return ownedRelationship;
    }

    public void setOwnedRelationship(List<Object> ownedRelationship) {
        this.ownedRelationship = ownedRelationship != null ? new ArrayList<>(ownedRelationship) : List.of();
    }

    public List<ObjectIdentity> getOwnedElement() {
        return ownedElement;
    }

    public void setOwnedElement(List<ObjectIdentity> ownedElement) {
        this.ownedElement = ownedElement != null ? new ArrayList<>(ownedElement) : List.of();
    }

    public ObjectIdentity getOwningNamespace() {
        return owningNamespace;
    }

    public void setOwningNamespace(ObjectIdentity owningNamespace) {
        this.owningNamespace = owningNamespace;
    }

    public List<ObjectIdentity> getOwnedSpecialization() {
        return ownedSpecialization;
    }

    public void setOwnedSpecialization(List<ObjectIdentity> ownedSpecialization) {
        this.ownedSpecialization = ownedSpecialization != null ? new ArrayList<>(ownedSpecialization) : List.of();
    }

    public Boolean getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Boolean isEnd) {
        this.isEnd = isEnd;
    }

    public List<ObjectIdentity> getOwnedSubsetting() {
        return ownedSubsetting;
    }

    public void setOwnedSubsetting(List<ObjectIdentity> ownedSubsetting) {
        this.ownedSubsetting = ownedSubsetting != null ? new ArrayList<>(ownedSubsetting) : List.of();
    }

    public ObjectIdentity getDeclaration() {
        return declaration;
    }

    public void setDeclaration(ObjectIdentity declaration) {
        this.declaration = declaration;
    }

    public List<ObjectIdentity> getOwnedReferenceSubsetting() {
        return ownedReferenceSubsetting;
    }

    public void setOwnedReferenceSubsetting(List<ObjectIdentity> ownedReferenceSubsetting) {
        this.ownedReferenceSubsetting = ownedReferenceSubsetting != null ? new ArrayList<>(ownedReferenceSubsetting) : List.of();
    }

    public List<ObjectIdentity> getReferencedFeature() {
        return referencedFeature;
    }

    public void setReferencedFeature(List<ObjectIdentity> referencedFeature) {
        this.referencedFeature = referencedFeature != null ? new ArrayList<>(referencedFeature) : List.of();
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

    public List<ObjectIdentity> getInheritedFeature() {
        return inheritedFeature;
    }

    public void setInheritedFeature(List<ObjectIdentity> inheritedFeature) {
        this.inheritedFeature = inheritedFeature != null ? new ArrayList<>(inheritedFeature) : List.of();
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id='" + getId() + '\'' +
                ", type='" + getElementType() + '\'' +
                ", identifier='" + getIdentifier() + '\'' +
                ", name='" + name + '\'' +
                ", qualifiedName='" + qualifiedName + '\'' +
                ", owner=" + owner +
                ", owningRelationship=" + owningRelationship +
                ", owningNamespace=" + owningNamespace +
                ", ownedRelationship=" + ownedRelationship.size() + " items" +
                ", ownedElement=" + ownedElement.size() + " items" +
                ", ownedSpecialization=" + ownedSpecialization.size() + " items" +
                ", isEnd=" + isEnd +
                ", ownedSubsetting=" + ownedSubsetting.size() + " items" +
                ", declaration=" + declaration +
                ", ownedReferenceSubsetting=" + ownedReferenceSubsetting.size() + " items" +
                ", referencedFeature=" + referencedFeature.size() + " items" +
                ", directedFeature=" + directedFeature.size() + " items" +
                ", feature=" + feature.size() + " items" +
                ", inheritedFeature=" + inheritedFeature.size() + " items" +
                '}';
    }
}
