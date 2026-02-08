package com.akaene.stpa.scs.parser.sysmlv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A FlowConnectionUsage models an actual transfer (values/items) between connected
 * parts, i.e., when a connection is specifically used as a flow.
 *
 * <p>SysML v2 RDF Definition:
 * A FlowConnectionUsage is a ConnectionUsage that is also a FlowUsage. It represents
 * a specific flow connection between parts of a system, modeling the actual transfer
 * of values or items between connected elements.</p>
 */
public class FlowConnectionUsage extends SysMLV2Element implements NamedElement, OwnedElement {

    private String name;
    private String qualifiedName;
    private ObjectIdentity owner;
    private ObjectIdentity owningNamespace;
    private final List<ObjectIdentity> ownedElement = new ArrayList<>();
    private final List<ObjectIdentity> ownedMember = new ArrayList<>();
    private final List<ObjectIdentity> ownedEndFeature = new ArrayList<>();
    private final List<ObjectIdentity> ownedFeature = new ArrayList<>();
    private final List<Object> directedFeature = new ArrayList<>();
    private final List<ObjectIdentity> feature = new ArrayList<>();
    private final List<ObjectIdentity> inheritedFeature = new ArrayList<>();
    private final List<ObjectIdentity> type = new ArrayList<>();
    private ObjectIdentity declaration;
    private final List<ObjectIdentity> definition = new ArrayList<>();
    private ObjectIdentity individualDefinition;
    private final List<ObjectIdentity> occurrenceDefinition = new ArrayList<>();
    private final List<ObjectIdentity> itemDefinition = new ArrayList<>();
    private final List<ObjectIdentity> partDefinition = new ArrayList<>();
    private final List<ObjectIdentity> source = new ArrayList<>();
    private final List<ObjectIdentity> target = new ArrayList<>();
    private final List<ObjectIdentity> relatedElement = new ArrayList<>();
    private final List<ObjectIdentity> connectorEnd = new ArrayList<>();
    private ObjectIdentity sourceFeature;
    private final List<ObjectIdentity> targetFeature = new ArrayList<>();
    private final List<ObjectIdentity> association = new ArrayList<>();
    private final List<ObjectIdentity> relatedFeature = new ArrayList<>();
    private final List<ObjectIdentity> connectionDefinition = new ArrayList<>();
    private final List<ObjectIdentity> parameter = new ArrayList<>();
    private final List<ObjectIdentity> behavior = new ArrayList<>();
    private final List<ObjectIdentity> actionDefinition = new ArrayList<>();
    private final List<ObjectIdentity> flowConnectionDefinition = new ArrayList<>();

    public FlowConnectionUsage() {
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

    public List<Object> getDirectedFeature() {
        return directedFeature;
    }

    public void setDirectedFeature(List<Object> directedFeature) {
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

    public List<ObjectIdentity> getInheritedFeature() {
        return inheritedFeature;
    }

    public void setInheritedFeature(List<ObjectIdentity> inheritedFeature) {
        this.inheritedFeature.clear();
        if (inheritedFeature != null) {
            this.inheritedFeature.addAll(inheritedFeature);
        }
    }

    public List<ObjectIdentity> getType() {
        return type;
    }

    public void setType(List<ObjectIdentity> type) {
        this.type.clear();
        if (type != null) {
            this.type.addAll(type);
        }
    }

    public ObjectIdentity getDeclaration() {
        return declaration;
    }

    public void setDeclaration(ObjectIdentity declaration) {
        this.declaration = declaration;
    }

    public List<ObjectIdentity> getDefinition() {
        return definition;
    }

    public void setDefinition(List<ObjectIdentity> definition) {
        this.definition.clear();
        if (definition != null) {
            this.definition.addAll(definition);
        }
    }

    public ObjectIdentity getIndividualDefinition() {
        return individualDefinition;
    }

    public void setIndividualDefinition(ObjectIdentity individualDefinition) {
        this.individualDefinition = individualDefinition;
    }

    public List<ObjectIdentity> getOccurrenceDefinition() {
        return occurrenceDefinition;
    }

    public void setOccurrenceDefinition(List<ObjectIdentity> occurrenceDefinition) {
        this.occurrenceDefinition.clear();
        if (occurrenceDefinition != null) {
            this.occurrenceDefinition.addAll(occurrenceDefinition);
        }
    }

    public List<ObjectIdentity> getItemDefinition() {
        return itemDefinition;
    }

    public void setItemDefinition(List<ObjectIdentity> itemDefinition) {
        this.itemDefinition.clear();
        if (itemDefinition != null) {
            this.itemDefinition.addAll(itemDefinition);
        }
    }

    public List<ObjectIdentity> getPartDefinition() {
        return partDefinition;
    }

    public void setPartDefinition(List<ObjectIdentity> partDefinition) {
        this.partDefinition.clear();
        if (partDefinition != null) {
            this.partDefinition.addAll(partDefinition);
        }
    }

    public List<ObjectIdentity> getSource() {
        return source;
    }

    public void setSource(List<ObjectIdentity> source) {
        this.source.clear();
        if (source != null) {
            this.source.addAll(source);
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

    public List<ObjectIdentity> getConnectorEnd() {
        return connectorEnd;
    }

    public void setConnectorEnd(List<ObjectIdentity> connectorEnd) {
        this.connectorEnd.clear();
        if (connectorEnd != null) {
            this.connectorEnd.addAll(connectorEnd);
        }
    }

    public ObjectIdentity getSourceFeature() {
        return sourceFeature;
    }

    public void setSourceFeature(ObjectIdentity sourceFeature) {
        this.sourceFeature = sourceFeature;
    }

    public List<ObjectIdentity> getTargetFeature() {
        return targetFeature;
    }

    public void setTargetFeature(List<ObjectIdentity> targetFeature) {
        this.targetFeature.clear();
        if (targetFeature != null) {
            this.targetFeature.addAll(targetFeature);
        }
    }

    public List<ObjectIdentity> getAssociation() {
        return association;
    }

    public void setAssociation(List<ObjectIdentity> association) {
        this.association.clear();
        if (association != null) {
            this.association.addAll(association);
        }
    }

    public List<ObjectIdentity> getRelatedFeature() {
        return relatedFeature;
    }

    public void setRelatedFeature(List<ObjectIdentity> relatedFeature) {
        this.relatedFeature.clear();
        if (relatedFeature != null) {
            this.relatedFeature.addAll(relatedFeature);
        }
    }

    public List<ObjectIdentity> getConnectionDefinition() {
        return connectionDefinition;
    }

    public void setConnectionDefinition(List<ObjectIdentity> connectionDefinition) {
        this.connectionDefinition.clear();
        if (connectionDefinition != null) {
            this.connectionDefinition.addAll(connectionDefinition);
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

    public List<ObjectIdentity> getBehavior() {
        return behavior;
    }

    public void setBehavior(List<ObjectIdentity> behavior) {
        this.behavior.clear();
        if (behavior != null) {
            this.behavior.addAll(behavior);
        }
    }

    public List<ObjectIdentity> getActionDefinition() {
        return actionDefinition;
    }

    public void setActionDefinition(List<ObjectIdentity> actionDefinition) {
        this.actionDefinition.clear();
        if (actionDefinition != null) {
            this.actionDefinition.addAll(actionDefinition);
        }
    }

    public List<ObjectIdentity> getFlowConnectionDefinition() {
        return flowConnectionDefinition;
    }

    public void setFlowConnectionDefinition(List<ObjectIdentity> flowConnectionDefinition) {
        this.flowConnectionDefinition.clear();
        if (flowConnectionDefinition != null) {
            this.flowConnectionDefinition.addAll(flowConnectionDefinition);
        }
    }

    @Override
    public String toString() {
        return "FlowConnectionUsage{" +
                "id='" + getId() + '\'' +
                ", type='" + this.getElementType() + '\'' +
                ", identifier='" + getIdentifier() + '\'' +
                ", name='" + name + '\'' +
                ", qualifiedName='" + qualifiedName + '\'' +
                ", owner=" + owner +
                ", owningNamespace=" + owningNamespace +
                ", ownedElement=" + ownedElement.size() + " items" +
                ", ownedMember=" + ownedMember.size() + " items" +
                ", ownedEndFeature=" + ownedEndFeature.size() + " items" +
                ", ownedFeature=" + ownedFeature.size() + " items" +
                ", directedFeature=" + directedFeature.size() + " items" +
                ", feature=" + feature.size() + " items" +
                ", inheritedFeature=" + inheritedFeature.size() + " items" +
                ", type=" + type.size() + " items" +
                ", declaration=" + declaration +
                ", definition=" + definition.size() + " items" +
                ", individualDefinition=" + individualDefinition +
                ", occurrenceDefinition=" + occurrenceDefinition.size() + " items" +
                ", itemDefinition=" + itemDefinition.size() + " items" +
                ", partDefinition=" + partDefinition.size() + " items" +
                ", source=" + source.size() + " items" +
                ", target=" + target.size() + " items" +
                ", relatedElement=" + relatedElement.size() + " items" +
                ", connectorEnd=" + connectorEnd.size() + " items" +
                ", sourceFeature=" + sourceFeature +
                ", targetFeature=" + targetFeature.size() + " items" +
                ", association=" + association.size() + " items" +
                ", relatedFeature=" + relatedFeature.size() + " items" +
                ", connectionDefinition=" + connectionDefinition.size() + " items" +
                ", parameter=" + parameter.size() + " items" +
                ", behavior=" + behavior.size() + " items" +
                ", actionDefinition=" + actionDefinition.size() + " items" +
                ", flowConnectionDefinition=" + flowConnectionDefinition.size() + " items" +
                '}';
    }
}
