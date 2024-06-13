package com.akaene.stpa.scs.parser.sysml;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnterpriseArchitectSysMLXMIParserTest {

    @Test
    void isEnterpriseArchitectFileReturnsTrueForEA() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model_EA.xml").toURI());
        assertTrue(EnterpriseArchitectSysMLXMIParser.isEnterpriseArchitectFile(input));
    }

    @Test
    void isEnterpriseArchitectFileReturnsFalseForNonEA() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model/model.xmi").toURI());
        assertFalse(EnterpriseArchitectSysMLXMIParser.isEnterpriseArchitectFile(input));
    }
}
