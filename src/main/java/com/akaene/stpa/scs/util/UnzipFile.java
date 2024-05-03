package com.akaene.stpa.scs.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnzipFile {

    /**
     * Unzips the specified file into the specified target directory.
     *
     * @param file   File to unzip
     * @param target Directory to unzip the file into
     */
    public static void unzip(ZipFile file, Path target) throws IOException {
        final Iterator<? extends ZipEntry> it = file.entries().asIterator();
        while (it.hasNext()) {
            final ZipEntry zipEntry = it.next();
            final File newFile = newFile(target.toFile(), zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }
                final FileOutputStream out = new FileOutputStream(newFile);
                file.getInputStream(zipEntry).transferTo(out);
            }
        }
    }

    /**
     * <a href="https://snyk.io/research/zip-slip-vulnerability">https://snyk.io/research/zip-slip-vulnerability</a>
     */
    private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
