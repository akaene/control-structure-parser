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
    private final List<ObjectIdentity> type = new ArrayList<>();
    private ObjectIdentity declaration;
    private final List<ObjectIdentity> definition = new ArrayList<>();
    private ObjectIdentity individualDefinition;
    private final List<ObjectIdentity> occurrenceDefinition = new ArrayList<>();
    private final List<ObjectIdentity> itemDefinition = new ArrayList<>();
    private final List<ObjectIdentity> partDefinition = new ArrayList<>();
    private final List<ObjectIdentity> ownedElement = new ArrayList<>();
    private final List<ObjectIdentity> ownedMember = new ArrayList<>();
    private final List<ObjectIdentity> ownedEndFeature = new ArrayList<>();
    private final List<ObjectIdentity> ownedFeature = new ArrayList<>();
    private final List<ObjectIdentity> directedFeature = new ArrayList<>();
    private final List<ObjectIdentity> feature = new ArrayList<>();
    private final List<ObjectIdentity> nestedUsage = new ArrayList<>();
    private final List<ObjectIdentity> nestedPart = new ArrayList<>();
    private final List<ObjectIdentity> nestedItem = new ArrayList<>();
    private final List<ObjectIdentity> nestedOccurrence = new ArrayList<>();
    private final List<ObjectIdentity> nestedAction = new ArrayList<>();
    private final List<ObjectIdentity> nestedConnection = new ArrayList<>();
    private final List<ObjectIdentity> nestedFlow = new ArrayList<>();

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
        this.partDefinition.clear();
        if (partDefinition != null) {
            this.partDefinition.addAll(partDefinition);
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

    public List<ObjectIdentity> getNestedUsage() {
        return nestedUsage;
    }

    public void setNestedUsage(List<ObjectIdentity> nestedUsage) {
        this.nestedUsage.clear();
        if (nestedUsage != null) {
            this.nestedUsage.addAll(nestedUsage);
        }
    }

    public List<ObjectIdentity> getNestedPart() {
        return nestedPart;
    }

    public void setNestedPart(List<ObjectIdentity> nestedPart) {
        this.nestedPart.clear();
        if (nestedPart != null) {
            this.nestedPart.addAll(nestedPart);
        }
    }

    public List<ObjectIdentity> getNestedItem() {
        return nestedItem;
    }

    public void setNestedItem(List<ObjectIdentity> nestedItem) {
        this.nestedItem.clear();
        if (nestedItem != null) {
            this.nestedItem.addAll(nestedItem);
        }
    }

    public List<ObjectIdentity> getNestedOccurrence() {
        return nestedOccurrence;
    }

    public void setNestedOccurrence(List<ObjectIdentity> nestedOccurrence) {
        this.nestedOccurrence.clear();
        if (nestedOccurrence != null) {
            this.nestedOccurrence.addAll(nestedOccurrence);
        }
    }

    public List<ObjectIdentity> getNestedAction() {
        return nestedAction;
    }

    public void setNestedAction(List<ObjectIdentity> nestedAction) {
        this.nestedAction.clear();
        if (nestedAction != null) {
            this.nestedAction.addAll(nestedAction);
        }
    }

    public List<ObjectIdentity> getNestedConnection() {
        return nestedConnection;
    }

    public void setNestedConnection(List<ObjectIdentity> nestedConnection) {
        this.nestedConnection.clear();
        if (nestedConnection != null) {
            this.nestedConnection.addAll(nestedConnection);
        }
    }

    public List<ObjectIdentity> getNestedFlow() {
        return nestedFlow;
    }

    public void setNestedFlow(List<ObjectIdentity> nestedFlow) {
        this.nestedFlow.clear();
        if (nestedFlow != null) {
            this.nestedFlow.addAll(nestedFlow);
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
