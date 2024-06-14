package com.akaene.stpa.scs.parser.sysml;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Optional;

class ZipModelFileFilter implements FilenameFilter {

    private static final String PROFILE_FILE_SUFFIX = ".profile";

    @Override
    public boolean accept(File dir, String name) {
        final Optional<String> extension = Arrays.stream(SysMLXMIParser.SUPPORTED_FILE_EXTENSIONS).filter(name::endsWith)
                                                 .findAny();
        return extension.isPresent() && !name.endsWith(PROFILE_FILE_SUFFIX + "." + extension.get());
    }
}
