package com.axiante.mui.common.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AxFileUtils {
    private static final Integer DEFAULT_BUFFER_SIZE=4096;
    public static String getChecksum(@NonNull final MessageDigest digest, @NonNull final File file) {
        try(final FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                digest.update(buffer, 0, len);
            }
            fis.close();
            byte[] bytes = digest.digest();
            final StringBuilder builder = new StringBuilder();
            for (byte aByte : bytes) {
                builder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return builder.toString();
        } catch (Exception ex) {
            log.warn("Error calculating checksum for file " + file.getName(), ex);
            return null;
        }
    }

    public static boolean writeToFile(@NonNull final String fullpath, @NonNull final String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fullpath),DEFAULT_BUFFER_SIZE);
            writer.write(content);
            writer.close();
            return true;
        } catch (IOException ex) {
            log.error("Unable to write content to file " + fullpath, ex);
            return false;
        }
    }
}
