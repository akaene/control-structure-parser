package com.akaene.stpa.scs.parser;

import com.akaene.stpa.scs.model.Model;

import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipFile;

/**
 * Parses control structure from input.
 */
public interface ControlStructureParser {

    /**
     * Parses control structure from the specified ZIP archive.
     * <p>
     * The archive should contain a file called {@literal model.ext} where {@literal ext} is the extensions
     * corresponding to the parser implementation (e.g., xmi for the XMI parser). This file is expected to contain the
     * model to parse.
     * <p>
     * The archive can be used to provide the XMI file together with additional profiles and namespaces.
     *
     * @param input ZIP file containing the model to parse.
     * @return Parsed model
     */
    Model parse(ZipFile input);

    /**
     * Parses control structure from the specified file.
     * <p>
     * Note that it is assumed that any additional namespaces and profiles are accessible from the file (e.g., they are
     * in the same directory).
     *
     * @param input File containing serialized control structure model
     * @return Parsed model
     */
    Model parse(File input);
}
