package com.akaene.stpa.scs.model;

/**
 * Marks classes that have a stable, tool-independent identifier used for model interchange
 * and cross-tool references.
 * <p>
 * The {@code identifier} should be unique **within the scope of its originating model** and
 * remain stable across imports, exports, and tool boundaries. It is typically represented as a UUID.
 * <p>
 * This identifier is used to reference elements across models or tools, for example linking
 * internal model elements to external ones, such as SysML model elements.
 * <p>
 * Note: Uniqueness is scoped to the model; different models may have overlapping identifiers.
 * When integrating elements from multiple models, additional context (such as the model ID or namespace)
 * should be used to disambiguate.
 */
public interface InterchangeIdentifiable {

    /**
     * Returns identifier suitable for interchange with other tools.
     * 
     * @return the interchange identifier
     */
    String getIdentifier();
}
