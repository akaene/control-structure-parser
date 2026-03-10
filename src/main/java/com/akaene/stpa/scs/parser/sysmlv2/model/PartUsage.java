package com.akaene.stpa.scs.parser.sysmlv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A PartUsage represents a specific part in a system model, used to instantiate a
 * structural type PartDefinition.
 *
 * <p>SysML v2 RDF Definition:
 * A PartUsage is an OccurrenceUsage whose definition is a Structure. Nominally, if the
 * definition is a PartDefinition, a PartUsage is a usage of that PartDefinition within a
 * system. However, other kinds of Kernel Structures are also allowed, to permit use of
 * Structures from the Kernel Model Libraries.</p>
 */
public class PartUsage extends SysMLV2Element implements NamedElement, OwnedElement {

    private String name;
    private String qualifiedName;
    private ObjectIdentity owningNamespace;
    private ObjectIdentity owner;
    private Boolean isComposite;
    private Boolean isIndividual;
    private Boolean isAbstract;
    private List<ObjectIdentity> type = List.of();
    private ObjectIdentity declaration;
    private List<ObjectIdentity> definition = List.of();
    private ObjectIdentity individualDefinition;
    private List<ObjectIdentity> occurrenceDefinition = List.of();
    private List<ObjectIdentity> itemDefinition = List.of();
    private List<ObjectIdentity> partDefinition = List.of();
    private List<ObjectIdentity> ownedElement = List.of();
    private List<ObjectIdentity> ownedMember = List.of();
    private List<ObjectIdentity> ownedEndFeature = List.of();
    private List<ObjectIdentity> ownedFeature = List.of();
    private List<ObjectIdentity> directedFeature = List.of();
    private List<ObjectIdentity> feature = List.of();
    private List<ObjectIdentity> nestedUsage = List.of();
    private List<ObjectIdentity> nestedPart = List.of();
    private List<ObjectIdentity> nestedItem = List.of();
    private List<ObjectIdentity> nestedOccurrence = List.of();
    private List<ObjectIdentity> nestedAction = List.of();
    private List<ObjectIdentity> nestedConnection = List.of();
    private List<ObjectIdentity> nestedFlow = List.of();

    public PartUsage() {
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

    public List<ObjectIdentity> getPartDefinition() {
        return partDefinition;
    }

    public void setPartDefinition(List<ObjectIdentity> partDefinition) {
        this.partDefinition = partDefinition != null ? new ArrayList<>(partDefinition) : List.of();
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

    public Boolean getIsIndividual() {
        return isIndividual;
    }

    public void setIsIndividual(Boolean isIndividual) {
        this.isIndividual = isIndividual;
    }

    public Boolean getIsAbstract() {
        return isAbstract;
    }

    public void setIsAbstract(Boolean isAbstract) {
        this.isAbstract = isAbstract;
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

    public List<ObjectIdentity> getNestedUsage() {
        return nestedUsage;
    }

    public void setNestedUsage(List<ObjectIdentity> nestedUsage) {
        this.nestedUsage = nestedUsage != null ? new ArrayList<>(nestedUsage) : List.of();
    }

    public List<ObjectIdentity> getNestedPart() {
        return nestedPart;
    }

    public void setNestedPart(List<ObjectIdentity> nestedPart) {
        this.nestedPart = nestedPart != null ? new ArrayList<>(nestedPart) : List.of();
    }

    public List<ObjectIdentity> getNestedItem() {
        return nestedItem;
    }

    public void setNestedItem(List<ObjectIdentity> nestedItem) {
        this.nestedItem = nestedItem != null ? new ArrayList<>(nestedItem) : List.of();
    }

    public List<ObjectIdentity> getNestedOccurrence() {
        return nestedOccurrence;
    }

    public void setNestedOccurrence(List<ObjectIdentity> nestedOccurrence) {
        this.nestedOccurrence = nestedOccurrence != null ? new ArrayList<>(nestedOccurrence) : List.of();
    }

    public List<ObjectIdentity> getNestedAction() {
        return nestedAction;
    }

    public void setNestedAction(List<ObjectIdentity> nestedAction) {
        this.nestedAction = nestedAction != null ? new ArrayList<>(nestedAction) : List.of();
    }

    public List<ObjectIdentity> getNestedConnection() {
        return nestedConnection;
    }

    public void setNestedConnection(List<ObjectIdentity> nestedConnection) {
        this.nestedConnection = nestedConnection != null ? new ArrayList<>(nestedConnection) : List.of();
    }

    public List<ObjectIdentity> getNestedFlow() {
        return nestedFlow;
    }

    public void setNestedFlow(List<ObjectIdentity> nestedFlow) {
        this.nestedFlow = nestedFlow != null ? new ArrayList<>(nestedFlow) : List.of();
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

    @Override
    public String toString() {
        return "PartUsage{" +
                "id='" + getId() + '\'' +
                ", type='" + getType() + '\'' +
                ", identifier='" + getIdentifier() + '\'' +
                ", name='" + name + '\'' +
                ", qualifiedName='" + qualifiedName + '\'' +
                ", owningNamespace=" + owningNamespace +
                ", owner=" + owner +
                ", isComposite=" + isComposite +
                ", isIndividual=" + isIndividual +
                ", isAbstract=" + isAbstract +
                ", type=" + type.size() + " items" +
                ", declaration=" + declaration +
                ", definition=" + definition.size() + " items" +
                ", individualDefinition=" + individualDefinition +
                ", occurrenceDefinition=" + occurrenceDefinition.size() + " items" +
                ", itemDefinition=" + itemDefinition.size() + " items" +
                ", partDefinition=" + partDefinition.size() + " items" +
                ", ownedElement=" + ownedElement.size() + " items" +
                ", ownedMember=" + ownedMember.size() + " items" +
                ", ownedEndFeature=" + ownedEndFeature.size() + " items" +
                ", ownedFeature=" + ownedFeature.size() + " items" +
                ", directedFeature=" + directedFeature.size() + " items" +
                ", feature=" + feature.size() + " items" +
                ", nestedUsage=" + nestedUsage.size() + " items" +
                ", nestedPart=" + nestedPart.size() + " items" +
                ", nestedItem=" + nestedItem.size() + " items" +
                ", nestedOccurrence=" + nestedOccurrence.size() + " items" +
                ", nestedAction=" + nestedAction.size() + " items" +
                ", nestedConnection=" + nestedConnection.size() + " items" +
                ", nestedFlow=" + nestedFlow.size() + " items" +
                '}';
    }
}
