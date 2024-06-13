package com.akaene.stpa.scs.parser;

import com.akaene.stpa.scs.model.Model;

import java.io.File;

/**
 * Parses control structure from input.
 */
public interface ControlStructureParser {

    /**
     * Parses control structure from the specified file.
     * <p>
     * Note that it is assumed that any additional namespaces and profiles are accessible from the file (e.g., they are
     * in the same directory or archive).
     *
     * @param input File containing serialized control structure model
     * @return Parsed model
     */
    Model parse(File input);
}
