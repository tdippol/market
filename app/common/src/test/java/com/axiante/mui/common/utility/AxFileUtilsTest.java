package com.axiante.mui.common.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.Test;

public class AxFileUtilsTest {

    @Test(expected = NullPointerException.class)
    public void getChecksum_givenNullDigest_shouldThrowException() {
        final File file = loadFile("sample_file.txt");
        final String checksum = AxFileUtils.getChecksum(null, file);
    }

    @Test(expected = NullPointerException.class)
    public void getChecksum_givenNullFile_shouldThrowException() throws NoSuchAlgorithmException {
        final String checksum = AxFileUtils.getChecksum(MessageDigest.getInstance("SHA-256"), null);
    }

    @Test
    public void getChecksum_givenNotExistingFile_shouldReturnNull() throws NoSuchAlgorithmException {
        final String checksum = AxFileUtils.getChecksum(MessageDigest.getInstance("SHA-256"), new File("foo.txt"));
        assertNull(checksum);
    }

    @Test
    public void getChecksum_givenValidFile_shouldReturnChecksum() throws NoSuchAlgorithmException {
        final File file = loadFile("sample_file.txt");
        final String checksum = AxFileUtils.getChecksum(MessageDigest.getInstance("SHA-256"), file);
        assertEquals("08b70efb7334be67b7af5379b81152a790715c20e583e2fddc8ec357e312fa95", checksum);
    }

    @Test(expected = NullPointerException.class)
    public void writeToFile_givenNullFullPath_shouldThrowException() {
        AxFileUtils.writeToFile(null, "foo");
    }

    @Test(expected = NullPointerException.class)
    public void writeToFile_givenNullContent_shouldThrowException() {
        AxFileUtils.writeToFile("foo.txt", null);
    }

    @Test
    public void writeToFile_shouldReturnFalse_whenUnableToWriteToFile() throws IOException {
        // Create temp folder
        final Path path = Files.createTempDirectory("junit-");
        final String fileFullPath = path.toString() + File.separator + "bar" + File.separator + "foo.txt";
        assertFalse(AxFileUtils.writeToFile(fileFullPath, "foo"));
        // Cleanup temp folder
        final File file = new File(fileFullPath);
        if (file.exists()) {
            file.delete();
        }
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @Test
    public void writeToFile_shouldReturnTrue_whenWriteToFileSuccessfully() throws IOException {
        // Create temp folder
        final Path path = Files.createTempDirectory("junit-");
        final String fileFullPath = path.toString() + File.separator + "foo.txt";
        assertTrue(AxFileUtils.writeToFile(fileFullPath, "foo"));
        // Cleanup temp folder
        final File file = new File(fileFullPath);
        if (file.exists()) {
            file.delete();
        }
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    private File loadFile(final String filename) {
        final URL resource = getClass().getClassLoader().getResource(filename);
        return new File(resource.getFile());
    }
}