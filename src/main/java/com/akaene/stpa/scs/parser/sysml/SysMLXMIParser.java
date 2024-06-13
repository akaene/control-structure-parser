package com.akaene.stpa.scs.parser.sysml;

import com.akaene.stpa.scs.model.Model;
import com.akaene.stpa.scs.parser.ControlStructureParser;

import java.io.File;

/**
 * Parses control structure from a SysML XMI artifact.
 * <p>
 * The parser supports SysML XMI and UML files with {@literal .xmi}, {@literal .uml} and {@literal .xml} extensions and
 * ZIP files. Note that a profile file may also be associated with the model, in which case the profile file needs to be
 * accessible from the provided file's directory or in the provided ZIP archive.
 */
public class SysMLXMIParser implements ControlStructureParser {

    public static String[] SUPPORTED_EXTENSIONS = {"xmi", "uml", "xml"};

    @Override
    public Model parse(File input) {
        if (UnzippingSysMLXMIParser.isZipFile(input)) {
            return new UnzippingSysMLXMIParser(this).parse(input);
        } else if (EnterpriseArchitectSysMLXMIParser.isEnterpriseArchitectFile(input)) {
            return new EnterpriseArchitectSysMLXMIParser().parse(input);
        } else {
            return new EMFSysMLXMIParser().parse(input);
        }
    }
}
