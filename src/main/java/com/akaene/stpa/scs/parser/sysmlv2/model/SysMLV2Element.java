package com.akaene.stpa.scs.parser.sysmlv2.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a base SysML v2 element with common attributes.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type",
        visible = true,
        defaultImpl = SysMLV2Element.class
)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = FlowConnectionUsage.class, name = "FlowConnectionUsage"),
        @JsonSubTypes.Type(value = ReferenceSubsetting.class, name = "ReferenceSubsetting"),
        @JsonSubTypes.Type(value = Feature.class, name = "Feature"),
        @JsonSubTypes.Type(value = PartDefinition.class, name = "PartDefinition"),
        @JsonSubTypes.Type(value = Package.class, name = "Package"),
        @JsonSubTypes.Type(value = PartUsage.class, name = "PartUsage"),
        @JsonSubTypes.Type(value = ReferenceUsage.class, name = "ReferenceUsage"),
        @JsonSubTypes.Type(value = RequirementUsage.class, name = "RequirementUsage"),
        @JsonSubTypes.Type(value = SatisfyRequirementUsage.class, name = "SatisfyRequirementUsage"),
        @JsonSubTypes.Type(value = FlowConnectionDefinition.class, name = "FlowConnectionDefinition")
})
public class SysMLV2Element extends ObjectIdentity {
    @JsonProperty("@type")
    private String elementType;
    private String identifier;
    private Map<String, Object> additionalProperties;
    private static final Logger LOG = LoggerFactory.getLogger(SysMLV2Element.class);

    public SysMLV2Element() {
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @JsonAnySetter
    public void add(String propertyName, Object value) {
        if (additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        additionalProperties.put(propertyName, value);
        LOG.warn(
                "Unrecognized property '{}' encountered for SysML v2 element type '{}' (id: '{}')." +
                        " Storing values of the property in 'additionalProperties'." +
                        " Consider extending class {} with the property to handle this property.",
                propertyName, elementType, getId(), this.getClass().getName()
        );
    }


    @JsonAnyGetter
    public Object get(String propertyName) {
        return additionalProperties != null ? additionalProperties.get(propertyName) : null;
    }
}
