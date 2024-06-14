package com.akaene.stpa.scs.parser.graphml;

import com.akaene.stpa.scs.model.Model;
import com.akaene.stpa.scs.parser.ControlStructureParser;

import java.io.File;

/**
 * Control structure parser for GraphML files (produced by yEd).
 */
public class GraphMLParser implements ControlStructureParser {

    public static final String FILE_EXTENSION = ".graphml";

    @Override
    public Model parse(File input) {
        return null;
    }

    @Override
    public boolean supports(File input) {
        return input.exists() && input.getName().endsWith(FILE_EXTENSION);
    }
}
