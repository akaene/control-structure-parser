package com.akaene.stpa.scs.parser;

import com.akaene.stpa.scs.model.Model;

import java.io.File;

/**
 * Allows running control structure parsing as a standalone application.
 */
public class ControlStructureParserRunner {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Expected exactly one argument");
        }
        new ControlStructureParserRunner().parseAndPrint(args[0]);
    }

    private void parseAndPrint(String path) {
        final File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("Specified path does not exist.");
        }
        final Model model = ControlStructureParsers.parse(file);
        System.out.println(model);
    }
}
