package com.akaene.stpa.scs.parser.sysml;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class EnterpriseArchitectTransformerTest {

    @Test
    void isEnterpriseArchitectFileReturnsTrueForEA() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model_EA.xml").toURI());
        assertTrue(EnterpriseArchitectTransformer.isEnterpriseArchitectFile(input));
    }

    @Test
    void isEnterpriseArchitectFileReturnsFalseForNonEA() throws Exception {
        final File input = new File(getClass().getClassLoader().getResource("simple-model/model.xmi").toURI());
        assertFalse(EnterpriseArchitectTransformer.isEnterpriseArchitectFile(input));
    }
}
