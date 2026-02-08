package com.akaene.stpa.scs.parser.sysmlv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A SatisfyRequirementUsage records that a subject satisfies a requirement, used to
 * capture requirement satisfaction links in the model.
 *
 * <p>SysML v2 RDF Definition:
 * A SatisfyRequirementUsage is a Usage that asserts that a satisfiedRequirement is
 * satisfied by a satisfyingSubject. It is also an AssertConstraintUsage with the
 * satisfiedRequirement as its assertedConstraint.</p>
 */
public class SatisfyRequirementUsage extends SysMLV2Element implements NamedElement, OwnedElement {

    private String name;
    private String qualifiedName;
    private ObjectIdentity owningNamespace;
    private ObjectIdentity owner;
    private final List<Object> ownedRelationship = new ArrayList<>();
    private final List<ObjectIdentity> ownedElement = new ArrayList<>();
    private final List<ObjectIdentity> ownedMember = new ArrayList<>();
    private final List<ObjectIdentity> ownedSpecialization = new ArrayList<>();
    private final List<ObjectIdentity> ownedSubsetting = new ArrayList<>();
    private ObjectIdentity declaration;
    private final List<ObjectIdentity> ownedReferenceSubsetting = new ArrayList<>();
    private final List<ObjectIdentity> referencedFeature = new ArrayList<>();
    private ObjectIdentity assertedConstraint;
    private ObjectIdentity satisfiedRequirement;
    private ObjectIdentity satisfyingFeature;

    public SatisfyRequirementUsage() {
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

    public List<Object> getOwnedRelationship() {
        return ownedRelationship;
    }

    public void setOwnedRelationship(List<Object> ownedRelationship) {
        this.ownedRelationship.clear();
        if (ownedRelationship != null) {
            this.ownedRelationship.addAll(ownedRelationship);
        }
    }

    public List<ObjectIdentity> getOwnedSpecialization() {
        return ownedSpecialization;
    }

    public void setOwnedSpecialization(List<ObjectIdentity> ownedSpecialization) {
        this.ownedSpecialization.clear();
        if (ownedSpecialization != null) {
            this.ownedSpecialization.addAll(ownedSpecialization);
        }
    }

    public List<ObjectIdentity> getOwnedSubsetting() {
        return ownedSubsetting;
    }

    public void setOwnedSubsetting(List<ObjectIdentity> ownedSubsetting) {
        this.ownedSubsetting.clear();
        if (ownedSubsetting != null) {
            this.ownedSubsetting.addAll(ownedSubsetting);
        }
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
        this.ownedReferenceSubsetting.clear();
        if (ownedReferenceSubsetting != null) {
            this.ownedReferenceSubsetting.addAll(ownedReferenceSubsetting);
        }
    }

    public List<ObjectIdentity> getReferencedFeature() {
        return referencedFeature;
    }

    public void setReferencedFeature(List<ObjectIdentity> referencedFeature) {
        this.referencedFeature.clear();
        if (referencedFeature != null) {
            this.referencedFeature.addAll(referencedFeature);
        }
    }

    public ObjectIdentity getAssertedConstraint() {
        return assertedConstraint;
    }

    public void setAssertedConstraint(ObjectIdentity assertedConstraint) {
        this.assertedConstraint = assertedConstraint;
    }

    public ObjectIdentity getSatisfiedRequirement() {
        return satisfiedRequirement;
    }

    public void setSatisfiedRequirement(ObjectIdentity satisfiedRequirement) {
        this.satisfiedRequirement = satisfiedRequirement;
    }

    public ObjectIdentity getSatisfyingFeature() {
        return satisfyingFeature;
    }

    public void setSatisfyingFeature(ObjectIdentity satisfyingFeature) {
        this.satisfyingFeature = satisfyingFeature;
    }

    @Override
    public String toString() {
        return "SatisfyRequirementUsage{" +
                "id='" + getId() + '\'' +
                ", type='" + getElementType() + '\'' +
                ", identifier='" + getIdentifier() + '\'' +
                ", name='" + name + '\'' +
                ", qualifiedName='" + qualifiedName + '\'' +
                ", owningNamespace=" + owningNamespace +
                ", owner=" + owner +
                ", ownedRelationship=" + ownedRelationship.size() + " items" +
                ", ownedElement=" + ownedElement.size() + " items" +
                ", ownedMember=" + ownedMember.size() + " items" +
                ", ownedSpecialization=" + ownedSpecialization.size() + " items" +
                ", ownedSubsetting=" + ownedSubsetting.size() + " items" +
                ", declaration=" + declaration +
                ", ownedReferenceSubsetting=" + ownedReferenceSubsetting.size() + " items" +
                ", referencedFeature=" + referencedFeature.size() + " items" +
                ", assertedConstraint=" + assertedConstraint +
                ", satisfiedRequirement=" + satisfiedRequirement +
                ", satisfyingFeature=" + satisfyingFeature +
                '}';
    }
}
