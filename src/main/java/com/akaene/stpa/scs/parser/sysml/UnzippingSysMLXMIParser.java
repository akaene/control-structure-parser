package com.akaene.stpa.scs.parser.sysml;

import com.akaene.stpa.scs.exception.ControlStructureParserException;
import com.akaene.stpa.scs.model.Model;
import com.akaene.stpa.scs.parser.ControlStructureParser;
import com.akaene.stpa.scs.util.UnzipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;
import java.util.zip.ZipFile;

/**
 * Control structure parser that supports ZIP archives.
 * <p>
 * It works by first unzipping the input archive into a temporary directory, then running a parser and returning its
 * result. The temporary directory is then removed.
 */
public class UnzippingSysMLXMIParser implements ControlStructureParser {

    private static final Logger LOG = LoggerFactory.getLogger(UnzippingSysMLXMIParser.class);

    public static final String ZIP_MIME_TYPE = "application/zip";

    private final ControlStructureParser decorated;

    public UnzippingSysMLXMIParser(ControlStructureParser decorated) {
        this.decorated = decorated;
    }

    @Override
    public Model parse(File input) {
        if (!isZipFile(input)) {
            throw new IllegalArgumentException("Expected a ZIP file.");
        }
        try {
            LOG.debug("Input is a ZIP file. Unzipping it first.");
            final ZipFile zipFile = new ZipFile(input);
            final Path tempDir = Files.createTempDirectory("sysml-xmi-parser");
            UnzipFile.unzip(zipFile, tempDir);
            final File[] models = tempDir.toFile().listFiles(new ZipModelFileFilter());
            if (models == null) {
                throw new ControlStructureParserException("Unable to unzip file.");
            }
            if (models.length != 1) {
                deleteTempUnzipDirectory(tempDir);
                throw new ControlStructureParserException(
                        "Expected a single model file in the archive, but found " + models.length);
            }
            final Model result = decorated.parse(models[0]);
            deleteTempUnzipDirectory(tempDir);
            return result;
        } catch (IOException e) {
            throw new ControlStructureParserException(
                    "Unable to create temp directory for extracting provided ZIP file.", e);
        }
    }

    private static void deleteTempUnzipDirectory(Path tempDir) throws IOException {
        try (final Stream<Path> toDelete = Files.walk(tempDir)) {
            toDelete.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    public static boolean isZipFile(File file) {
        try {
            return file != null && file.exists() && file.isFile() && ZIP_MIME_TYPE.equals(
                    Files.probeContentType(file.toPath()));
        } catch (IOException e) {
            throw new ControlStructureParserException("Unable to determine file type.", e);
        }
    }
}
