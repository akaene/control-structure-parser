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
    private final List<ObjectIdentity> ownedElement = new ArrayList<>();
    private final List<ObjectIdentity> ownedMember = new ArrayList<>();
    private final List<ObjectIdentity> ownedEndFeature = new ArrayList<>();
    private final List<ObjectIdentity> ownedFeature = new ArrayList<>();
    private final List<ObjectIdentity> directedFeature = new ArrayList<>();
    private final List<ObjectIdentity> feature = new ArrayList<>();
    private final List<ObjectIdentity> ownedUsage = new ArrayList<>();
    private final List<ObjectIdentity> ownedReference = new ArrayList<>();
    private final List<ObjectIdentity> target = new ArrayList<>();
    private final List<ObjectIdentity> relatedElement = new ArrayList<>();
    private final List<ObjectIdentity> associationEnd = new ArrayList<>();
    private final List<ObjectIdentity> targetType = new ArrayList<>();
    private final List<ObjectIdentity> relatedType = new ArrayList<>();
    private final List<ObjectIdentity> connectionEnd = new ArrayList<>();
    private final List<ObjectIdentity> parameter = new ArrayList<>();

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

    public List<ObjectIdentity> getOwnedEndFeature() {
        return ownedEndFeature;
    }

    public void setOwnedEndFeature(List<ObjectIdentity> ownedEndFeature) {
        this.ownedEndFeature.clear();
        if (ownedEndFeature != null) {
            this.ownedEndFeature.addAll(ownedEndFeature);
        }
    }

    public List<ObjectIdentity> getOwnedFeature() {
        return ownedFeature;
    }

    public void setOwnedFeature(List<ObjectIdentity> ownedFeature) {
        this.ownedFeature.clear();
        if (ownedFeature != null) {
            this.ownedFeature.addAll(ownedFeature);
        }
    }

    public List<ObjectIdentity> getDirectedFeature() {
        return directedFeature;
    }

    public void setDirectedFeature(List<ObjectIdentity> directedFeature) {
        this.directedFeature.clear();
        if (directedFeature != null) {
            this.directedFeature.addAll(directedFeature);
        }
    }

    public List<ObjectIdentity> getFeature() {
        return feature;
    }

    public void setFeature(List<ObjectIdentity> feature) {
        this.feature.clear();
        if (feature != null) {
            this.feature.addAll(feature);
        }
    }

    public List<ObjectIdentity> getOwnedUsage() {
        return ownedUsage;
    }

    public void setOwnedUsage(List<ObjectIdentity> ownedUsage) {
        this.ownedUsage.clear();
        if (ownedUsage != null) {
            this.ownedUsage.addAll(ownedUsage);
        }
    }

    public List<ObjectIdentity> getOwnedReference() {
        return ownedReference;
    }

    public void setOwnedReference(List<ObjectIdentity> ownedReference) {
        this.ownedReference.clear();
        if (ownedReference != null) {
            this.ownedReference.addAll(ownedReference);
        }
    }

    public List<ObjectIdentity> getTarget() {
        return target;
    }

    public void setTarget(List<ObjectIdentity> target) {
        this.target.clear();
        if (target != null) {
            this.target.addAll(target);
        }
    }

    public List<ObjectIdentity> getRelatedElement() {
        return relatedElement;
    }

    public void setRelatedElement(List<ObjectIdentity> relatedElement) {
        this.relatedElement.clear();
        if (relatedElement != null) {
            this.relatedElement.addAll(relatedElement);
        }
    }

    public List<ObjectIdentity> getAssociationEnd() {
        return associationEnd;
    }

    public void setAssociationEnd(List<ObjectIdentity> associationEnd) {
        this.associationEnd.clear();
        if (associationEnd != null) {
            this.associationEnd.addAll(associationEnd);
        }
    }

    public List<ObjectIdentity> getTargetType() {
        return targetType;
    }

    public void setTargetType(List<ObjectIdentity> targetType) {
        this.targetType.clear();
        if (targetType != null) {
            this.targetType.addAll(targetType);
        }
    }

    public List<ObjectIdentity> getRelatedType() {
        return relatedType;
    }

    public void setRelatedType(List<ObjectIdentity> relatedType) {
        this.relatedType.clear();
        if (relatedType != null) {
            this.relatedType.addAll(relatedType);
        }
    }

    public List<ObjectIdentity> getConnectionEnd() {
        return connectionEnd;
    }

    public void setConnectionEnd(List<ObjectIdentity> connectionEnd) {
        this.connectionEnd.clear();
        if (connectionEnd != null) {
            this.connectionEnd.addAll(connectionEnd);
        }
    }

    public List<ObjectIdentity> getParameter() {
        return parameter;
    }

    public void setParameter(List<ObjectIdentity> parameter) {
        this.parameter.clear();
        if (parameter != null) {
            this.parameter.addAll(parameter);
        }
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
