package com.akaene.stpa.scs.parser.sysml;

import com.akaene.stpa.scs.model.Model;
import com.akaene.stpa.scs.parser.ControlStructureParser;

import java.io.File;
import java.util.List;

/**
 * Parses control structure from a SysML XMI artifact.
 * <p>
 * The parser supports SysML XMI and UML files with {@literal .xmi}, {@literal .uml} and {@literal .xml} extensions and
 * ZIP files. Note that a profile file may also be associated with the model, in which case the profile file needs to be
 * accessible from the provided file's directory or in the provided ZIP archive.
 */
public class SysMLXMIParser implements ControlStructureParser {

    public static String[] SUPPORTED_FILE_EXTENSIONS = {"xmi", "uml", "xml"};

    private final List<ControlStructureParser> parsers = List.of(
            new UnzippingSysMLXMIParser(this),
            new EnterpriseArchitectSysMLXMIParser(),
            new EMFSysMLXMIParser()
    );

    @Override
    public Model parse(File input) {
        return parsers.stream().filter(p -> p.supports(input)).findFirst().map(p -> p.parse(input))
                      .orElseThrow(() -> new IllegalArgumentException("This parser does not support file " + input.getName()));
    }

    @Override
    public boolean supports(File input) {
        return input.exists() && parsers.stream().anyMatch(p -> p.supports(input));
    }
}
