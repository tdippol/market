package com.axiante.mui.common.utility;

import com.axiante.mui.common.utility.ftp.FtpUploader;
import com.axiante.mui.common.utility.ftp.FtpUploaderException;
import java.io.File;
import java.net.URL;

public class ManualFtpIntegrationTest {
    public static void main(String[] args) {
        // Configure your FTP settings here
        FtpUploader.FtpConfig config = FtpUploader.FtpConfig.builder()
                .server("192.168.0.24")     // Replace with your FTP server address
                .port(21)                      // Standard FTP port is 21
                .username("axiante")     // Replace with your username
                .password("Carrello")     // Replace with your password
                .remotePath("upload/location/")    // Directory to upload to
                .timeout(30000)                // 30 seconds timeout
                .build();

        // Path to the local file you want to upload
        URL url = ManualFtpIntegrationTest.class.getClassLoader().getResource("test.png");
        File fileToUpload = new File(url.getFile());

        // Name to save the file as on the FTP server
        String remoteFileName = fileToUpload.getName();  // Replace if needed

        System.out.println("Starting test upload...");
        System.out.println("File to upload: " + fileToUpload.getAbsolutePath());
        System.out.println("File exists: " + fileToUpload.exists());
        System.out.println("File size: " + fileToUpload.length() + " bytes");

        long startTime = System.currentTimeMillis();
        boolean result = false;
        try {
            // Execute the upload
            FtpUploader.uploadFile(config, remoteFileName, fileToUpload);
            result = true;
        } catch (FtpUploaderException e){
            System.err.printf("FTP upload failed: %s%n", e.getItalianMessage());
        }
        catch (Exception e) {
            System.err.println("Error during upload test:");
            e.printStackTrace();
        } finally{
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            System.out.println("Upload result: " + (result ? "SUCCESS" : "FAILED"));
            System.out.println("Upload took " + duration + " ms");
        }
    }
}
