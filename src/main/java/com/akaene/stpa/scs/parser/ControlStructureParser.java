package com.akaene.stpa.scs.parser;

import com.akaene.stpa.scs.model.Association;
import com.akaene.stpa.scs.model.ComponentType;
import com.akaene.stpa.scs.model.Connector;
import com.akaene.stpa.scs.model.Model;
import com.akaene.stpa.scs.model.Stereotype;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;

/**
 * Parses control structure from input.
 */
public interface ControlStructureParser {

    /**
     * Parses control structure from the specified input stream.
     *
     * @param input Stream containing serialized control structure model
     */
    Model parse(InputStream input);

    /**
     * Parses control structure from the specified file.
     *
     * @param input File containing serialized control structure model
     */
    Model parse(File input);
}
