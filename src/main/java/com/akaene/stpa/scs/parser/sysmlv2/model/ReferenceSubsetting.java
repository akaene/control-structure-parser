package com.akaene.stpa.scs.parser.sysmlv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A ReferenceSubsetting constrains a reference feature to be a subset of the referenced
 * feature, used to narrow which referenced instances are allowed.
 *
 * <p>SysML v2 RDF Definition:
 * ReferenceSubsetting is a Subsetting in which the subsettingFeature is a referencingFeature
 * and the subsettedFeature is the referencedFeature. This means that the set of instances
 * of the referencingFeature is a subset of the set of instances of the referencedFeature,
 * and the referencingFeature is a reference to the referencedFeature.</p>
 *
 * <p>Subsetting Definition:
 * Subsetting is Specialization in which the specific and general Types are Features. This means
 * all values of the subsettingFeature (on instances of its domain, i.e., the intersection of its
 * featuringTypes) are values of the subsettedFeature on instances of its domain. To support this
 * the domain of the subsettingFeature must be the same or specialize (at least indirectly) the
 * domain of the subsettedFeature (via Specialization), and the co-domain (intersection of the
 * types) of the subsettingFeature must specialize the co-domain of the subsettedFeature.</p>
 */
public class ReferenceSubsetting extends SysMLV2Element implements NamedElement {

    private String name;
    private String qualifiedName;
    private ObjectIdentity referencedFeature;
    private ObjectIdentity owningRelationship;
    private ObjectIdentity sourceFeature;
    private ObjectIdentity owner;
    private ObjectIdentity owningNamespace;
    private ObjectIdentity owningRelatedElement;
    private ObjectIdentity general;
    private ObjectIdentity specific;
    private ObjectIdentity owningType;
    private ObjectIdentity subsettedFeature;
    private ObjectIdentity subsettingFeature;
    private ObjectIdentity referencingFeature;
    private List<ObjectIdentity> source = List.of();
    private List<ObjectIdentity> target = List.of();
    private List<ObjectIdentity> relatedElement = List.of();
    private List<ObjectIdentity> ownedRelatedElement = List.of();
    private List<ObjectIdentity> ownedRelationship = List.of();
    private List<ObjectIdentity> ownedElement = List.of();

    public ReferenceSubsetting() {
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

    public ObjectIdentity getReferencedFeature() {
        return referencedFeature;
    }

    public void setReferencedFeature(ObjectIdentity referencedFeature) {
        this.referencedFeature = referencedFeature;
    }

    public ObjectIdentity getOwningRelationship() {
        return owningRelationship;
    }

    public void setOwningRelationship(ObjectIdentity owningRelationship) {
        this.owningRelationship = owningRelationship;
    }

    public ObjectIdentity getSourceFeature() {
        return sourceFeature;
    }

    public void setSourceFeature(ObjectIdentity sourceFeature) {
        this.sourceFeature = sourceFeature;
    }

    public List<ObjectIdentity> getOwnedRelatedElement() {
        return ownedRelatedElement;
    }

    public void setOwnedRelatedElement(List<ObjectIdentity> ownedRelatedElement) {
        this.ownedRelatedElement = ownedRelatedElement != null ? new ArrayList<>(ownedRelatedElement) : List.of();
    }

    public List<ObjectIdentity> getOwnedRelationship() {
        return ownedRelationship;
    }

    public void setOwnedRelationship(List<ObjectIdentity> ownedRelationship) {
        this.ownedRelationship = ownedRelationship != null ? new ArrayList<>(ownedRelationship) : List.of();
    }

    public List<ObjectIdentity> getOwnedElement() {
        return ownedElement;
    }

    public void setOwnedElement(List<ObjectIdentity> ownedElement) {
        this.ownedElement = ownedElement != null ? new ArrayList<>(ownedElement) : List.of();
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

    public ObjectIdentity getOwningRelatedElement() {
        return owningRelatedElement;
    }

    public void setOwningRelatedElement(ObjectIdentity owningRelatedElement) {
        this.owningRelatedElement = owningRelatedElement;
    }

    public ObjectIdentity getGeneral() {
        return general;
    }

    public void setGeneral(ObjectIdentity general) {
        this.general = general;
    }

    public ObjectIdentity getSpecific() {
        return specific;
    }

    public void setSpecific(ObjectIdentity specific) {
        this.specific = specific;
    }

    public ObjectIdentity getOwningType() {
        return owningType;
    }

    public void setOwningType(ObjectIdentity owningType) {
        this.owningType = owningType;
    }

    public ObjectIdentity getSubsettedFeature() {
        return subsettedFeature;
    }

    public void setSubsettedFeature(ObjectIdentity subsettedFeature) {
        this.subsettedFeature = subsettedFeature;
    }

    public ObjectIdentity getSubsettingFeature() {
        return subsettingFeature;
    }

    public void setSubsettingFeature(ObjectIdentity subsettingFeature) {
        this.subsettingFeature = subsettingFeature;
    }

    public ObjectIdentity getReferencingFeature() {
        return referencingFeature;
    }

    public void setReferencingFeature(ObjectIdentity referencingFeature) {
        this.referencingFeature = referencingFeature;
    }

    public List<ObjectIdentity> getSource() {
        return source;
    }

    public void setSource(List<ObjectIdentity> source) {
        this.source = source != null ? new ArrayList<>(source) : List.of();
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

    @Override
    public String toString() {
        return "ReferenceSubsetting{" +
                "id='" + getId() + '\'' +
                ", type='" + getElementType() + '\'' +
                ", identifier='" + getIdentifier() + '\'' +
                ", name='" + name + '\'' +
                ", qualifiedName='" + qualifiedName + '\'' +
                ", owner=" + owner +
                ", owningNamespace=" + owningNamespace +
                ", sourceFeature=" + sourceFeature +
                ", referencedFeature=" + referencedFeature +
                ", owningRelatedElement=" + owningRelatedElement +
                ", general=" + general +
                ", specific=" + specific +
                ", owningType=" + owningType +
                ", subsettedFeature=" + subsettedFeature +
                ", subsettingFeature=" + subsettingFeature +
                ", referencingFeature=" + referencingFeature +
                ", source=" + source.size() + " items" +
                ", target=" + target.size() + " items" +
                ", relatedElement=" + relatedElement.size() + " items" +
                ", ownedRelatedElement=" + ownedRelatedElement.size() + " items" +
                ", ownedRelationship=" + ownedRelationship.size() + " items" +
                ", ownedElement=" + ownedElement.size() + " items" +
                '}';
    }
}
