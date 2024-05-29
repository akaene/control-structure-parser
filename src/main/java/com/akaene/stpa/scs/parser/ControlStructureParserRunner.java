package com.akaene.stpa.scs.parser;

import com.akaene.stpa.scs.model.Model;
import com.akaene.stpa.scs.parser.sysml.SysMLXMIParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipFile;

/**
 * Allows running control structure parsing as a standalone application.
 */
public class ControlStructureParserRunner {

    private static final String ZIP_MIME_TYPE = "application/zip";

    private final ControlStructureParser parser = new SysMLXMIParser();

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
        try {
            final String contentType = Files.probeContentType(file.toPath());
            final Model model;
            if (ZIP_MIME_TYPE.equals(contentType)) {
                final ZipFile zipFile = new ZipFile(file);
                model = parser.parse(zipFile);
            } else {
                model = parser.parse(file);
            }
            System.out.println(model);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to determine content type.", e);
        }

    }
}
