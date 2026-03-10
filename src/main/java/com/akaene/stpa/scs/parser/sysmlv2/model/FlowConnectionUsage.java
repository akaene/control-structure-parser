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
    private List<ObjectIdentity> ownedElement = List.of();
    private List<ObjectIdentity> ownedMember = List.of();
    private List<ObjectIdentity> ownedEndFeature = List.of();
    private List<ObjectIdentity> ownedFeature = List.of();
    private List<Object> directedFeature = List.of();
    private List<ObjectIdentity> feature = List.of();
    private List<ObjectIdentity> inheritedFeature = List.of();
    private List<ObjectIdentity> type = List.of();
    private ObjectIdentity declaration;
    private List<ObjectIdentity> definition = List.of();
    private ObjectIdentity individualDefinition;
    private List<ObjectIdentity> occurrenceDefinition = List.of();
    private List<ObjectIdentity> itemDefinition = List.of();
    private List<ObjectIdentity> partDefinition = List.of();
    private List<ObjectIdentity> source = List.of();
    private List<ObjectIdentity> target = List.of();
    private List<ObjectIdentity> relatedElement = List.of();
    private List<ObjectIdentity> connectorEnd = List.of();
    private ObjectIdentity sourceFeature;
    private List<ObjectIdentity> targetFeature = List.of();
    private List<ObjectIdentity> association = List.of();
    private List<ObjectIdentity> relatedFeature = List.of();
    private List<ObjectIdentity> connectionDefinition = List.of();
    private List<ObjectIdentity> parameter = List.of();
    private List<ObjectIdentity> behavior = List.of();
    private List<ObjectIdentity> actionDefinition = List.of();
    private List<ObjectIdentity> flowConnectionDefinition = List.of();

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

    public List<Object> getDirectedFeature() {
        return directedFeature;
    }

    public void setDirectedFeature(List<Object> directedFeature) {
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

    public List<ObjectIdentity> getType() {
        return type;
    }

    public void setType(List<ObjectIdentity> type) {
        this.type = type != null ? new ArrayList<>(type) : List.of();
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
        this.definition = definition != null ? new ArrayList<>(definition) : List.of();
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
        this.occurrenceDefinition = occurrenceDefinition != null ? new ArrayList<>(occurrenceDefinition) : List.of();
    }

    public List<ObjectIdentity> getItemDefinition() {
        return itemDefinition;
    }

    public void setItemDefinition(List<ObjectIdentity> itemDefinition) {
        this.itemDefinition = itemDefinition != null ? new ArrayList<>(itemDefinition) : List.of();
    }

    public List<ObjectIdentity> getPartDefinition() {
        return partDefinition;
    }

    public void setPartDefinition(List<ObjectIdentity> partDefinition) {
        this.partDefinition = partDefinition != null ? new ArrayList<>(partDefinition) : List.of();
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

    public List<ObjectIdentity> getConnectorEnd() {
        return connectorEnd;
    }

    public void setConnectorEnd(List<ObjectIdentity> connectorEnd) {
        this.connectorEnd = connectorEnd != null ? new ArrayList<>(connectorEnd) : List.of();
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
        this.targetFeature = targetFeature != null ? new ArrayList<>(targetFeature) : List.of();
    }

    public List<ObjectIdentity> getAssociation() {
        return association;
    }

    public void setAssociation(List<ObjectIdentity> association) {
        this.association = association != null ? new ArrayList<>(association) : List.of();
    }

    public List<ObjectIdentity> getRelatedFeature() {
        return relatedFeature;
    }

    public void setRelatedFeature(List<ObjectIdentity> relatedFeature) {
        this.relatedFeature = relatedFeature != null ? new ArrayList<>(relatedFeature) : List.of();
    }

    public List<ObjectIdentity> getConnectionDefinition() {
        return connectionDefinition;
    }

    public void setConnectionDefinition(List<ObjectIdentity> connectionDefinition) {
        this.connectionDefinition = connectionDefinition != null ? new ArrayList<>(connectionDefinition) : List.of();
    }

    public List<ObjectIdentity> getParameter() {
        return parameter;
    }

    public void setParameter(List<ObjectIdentity> parameter) {
        this.parameter = parameter != null ? new ArrayList<>(parameter) : List.of();
    }

    public List<ObjectIdentity> getBehavior() {
        return behavior;
    }

    public void setBehavior(List<ObjectIdentity> behavior) {
        this.behavior = behavior != null ? new ArrayList<>(behavior) : List.of();
    }

    public List<ObjectIdentity> getActionDefinition() {
        return actionDefinition;
    }

    public void setActionDefinition(List<ObjectIdentity> actionDefinition) {
        this.actionDefinition = actionDefinition != null ? new ArrayList<>(actionDefinition) : List.of();
    }

    public List<ObjectIdentity> getFlowConnectionDefinition() {
        return flowConnectionDefinition;
    }

    public void setFlowConnectionDefinition(List<ObjectIdentity> flowConnectionDefinition) {
        this.flowConnectionDefinition = flowConnectionDefinition != null ? new ArrayList<>(flowConnectionDefinition) : List.of();
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
