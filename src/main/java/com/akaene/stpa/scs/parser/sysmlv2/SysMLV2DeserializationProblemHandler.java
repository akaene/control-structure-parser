package com.akaene.stpa.scs.parser.sysmlv2;

import com.akaene.stpa.scs.parser.sysmlv2.model.SysMLV2Element;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Handles deserialization problems in SysML v2 JSON artifacts.
 * <p>
 * Unknown SysML v2 element types are mapped to {@link SysMLV2Element}.
 * Unknown properties are stored in the 'additionalProperties' field of the {@link SysMLV2Element}.
 */
class SysMLV2DeserializationProblemHandler extends DeserializationProblemHandler {

    private static final Logger LOG = LoggerFactory.getLogger(SysMLV2DeserializationProblemHandler.class);

    @Override
    public boolean handleUnknownProperty(
            DeserializationContext ctxt,
            JsonParser p,
            JsonDeserializer<?> deserializer,
            Object beanOrClass,
            String propertyName
    ) throws IOException {
        if (beanOrClass instanceof SysMLV2Element element) {
            Object value = p.readValueAs(Object.class);
            element.add(propertyName, value);
            if (!element.getClass().equals(SysMLV2Element.class)) {
                LOG.warn(
                        "Unrecognized property '{}' encountered for SysML v2 element type '{}' (id: '{}')." +
                                " Storing values of the property in 'additionalProperties'." +
                                " Consider extending class {} with the property to handle this property.",
                        propertyName, element.getElementType(), element.getId(), element.getClass().getName()
                );
            }
            return true;
        }
        return false;
    }

    @Override
    public JavaType handleUnknownTypeId(
            DeserializationContext ctxt,
            JavaType baseType,
            String subTypeId,
            TypeIdResolver idResolver,
            String failureMsg
    ) throws IOException {
        if (baseType != null && baseType.hasRawClass(SysMLV2Element.class)) {
            LOG.debug(
                    "Unrecognized SysML v2 element type '{}'. Falling back to {}.",
                    subTypeId, SysMLV2Element.class.getSimpleName()
            );
            return ctxt.getTypeFactory().constructType(SysMLV2Element.class);
        }
        return null;
    }

    @Override
    public JavaType handleMissingTypeId(
            DeserializationContext ctxt,
            JavaType baseType,
            TypeIdResolver idResolver,
            String failureMsg
    ) throws IOException {
        if (baseType != null && baseType.hasRawClass(SysMLV2Element.class)) {
            LOG.warn(
                    "Missing SysML v2 element type id. Falling back to {}.",
                    SysMLV2Element.class.getSimpleName()
            );
            return ctxt.getTypeFactory().constructType(SysMLV2Element.class);
        }
        return null;
    }
}
