package com.axiante.mui.common.utility.ftp;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class FtpUploader {

    private static final int DEFAULT_TIMEOUT = 30000; // 30 seconds
    private static final int MAX_CONCURRENT_CONNECTIONS = 10;

    /**
     * Regular expression pattern to parse the PASV command response.
     *
     * When an FTP client sends a PASV command, the server responds with an address and port
     * encoded in the format: 227 Entering Passive Mode (h1,h2,h3,h4,p1,p2)
     *
     * Where:
     * - h1,h2,h3,h4: The four octets of the IP address where the server is listening
     * - p1,p2: The port number encoded as two bytes, where port = (p1 * 256) + p2
     *
     * Example response: "227 Entering Passive Mode (192,168,1,100,23,45)"
     * This means the server is listening on IP 192.168.1.100, port (23*256)+45 = 5933
     *
     * The pattern captures each of these six numbers as separate capturing groups,
     * allowing them to be extracted and used to establish the data connection.
     */
    private static final Pattern PASV_PATTERN = Pattern.compile("\\((\\d+),(\\d+),(\\d+),(\\d+),(\\d+),(\\d+)\\)");

    // Rate limiter to prevent too many concurrent connections
    private static final Semaphore connectionSemaphore = new Semaphore(MAX_CONCURRENT_CONNECTIONS);

    @Data
    @Builder
    public static class FtpConfig {
        private String server;
        private int port;
        private String username;
        private String password;
        private String remotePath;
        private int timeout;
    }

    /**
     * Uploads a file to an FTP server
     *
     * @param config FTP configuration details
     * @param fileName Name to save the file as on the server
     * @param file The file to upload
     * @throws FtpUploaderException if any error occurs during the upload process
     */
    public static void uploadFile(FtpConfig config, String fileName, File file) throws FtpUploaderException {
        if (file == null || !file.exists() || !file.canRead()) {
            log.error("File does not exist or cannot be read");
            throw new FtpUploaderException(
                    "Source file does not exist or cannot be read",
                    "Il file sorgente non esiste o non può essere letto"
            );
        }
        boolean acquired = false;
        try {
            // Try to acquire a connection permit with timeout
            acquired = connectionSemaphore.tryAcquire(1, 30, TimeUnit.SECONDS);
            if (!acquired) {
                log.error("Failed to acquire connection permit after timeout");
                throw new FtpUploaderException(
                        "Server busy, too many concurrent uploads",
                        "Server occupato, troppe richieste di caricamento simultanee"
                );
            }

            doUpload(config, fileName, file);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Thread interrupted while waiting for connection permit", e);
            throw new FtpUploaderException(
                    "Upload process was interrupted",
                    "Il processo di caricamento è stato interrotto",
                    e
            );
        } finally {
            if (acquired) {
                connectionSemaphore.release();
            }
        }
    }

    private static void doUpload(FtpConfig config, String fileName, File file) throws FtpUploaderException {
        Socket controlSocket = null;
        try {
            // Connect with timeout
            controlSocket = new Socket();
            controlSocket.connect(new InetSocketAddress(config.getServer(), config.getPort()),
                    config.getTimeout() > 0 ? config.getTimeout() : DEFAULT_TIMEOUT);
            controlSocket.setSoTimeout(config.getTimeout() > 0 ? config.getTimeout() : DEFAULT_TIMEOUT);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(controlSocket.getInputStream(), StandardCharsets.UTF_8));
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(controlSocket.getOutputStream(), StandardCharsets.UTF_8));

            // Read welcome message
            FtpResponse welcomeResponse = readResponse(reader);
            if (!welcomeResponse.isPositiveCompletion()) {
                log.error("Negative response from server: {}", welcomeResponse);
                throw new FtpUploaderException(
                        "Connection to FTP server failed: " + welcomeResponse,
                        "Connessione al server FTP fallita: " + welcomeResponse
                );
            }

            // Login sequence
            doLogin(reader, writer, config.getUsername(), config.getPassword());

            // Set binary mode
            sendCommand(writer, "TYPE I");
            FtpResponse typeResponse = readResponse(reader);
            if (!typeResponse.isPositiveCompletion()) {
                log.error("Failed to set binary mode: {}", typeResponse);
                throw new FtpUploaderException(
                        "Failed to set binary transfer mode: " + typeResponse,
                        "Impossibile impostare la modalità di trasferimento binario: " + typeResponse
                );
            }

            // Create directory structure if needed
            ensureDirectoryExists(reader, writer, config.getRemotePath());

            // Enter passive mode and get data connection
            Socket dataSocket = initiatePassiveMode(reader, writer);
            if (dataSocket == null) {
                throw new FtpUploaderException(
                        "Failed to establish data connection in passive mode",
                        "Impossibile stabilire la connessione dati in modalità passiva"
                );
            }

            try {
                // Send STOR command
                sendCommand(writer, "STOR " + fileName);
                FtpResponse storResponse = readResponse(reader);
                if (!storResponse.isPositivePreliminary()) {
                    log.error("Failed to initiate file transfer: {}", storResponse);
                    throw new FtpUploaderException(
                            "Failed to initiate file transfer: " + storResponse,
                            "Impossibile avviare il trasferimento del file: " + storResponse
                    );
                }

                // Upload file data
                try (OutputStream dataOut = dataSocket.getOutputStream();
                     FileInputStream fileIn = new FileInputStream(file)) {

                    byte[] buffer = new byte[8192]; // Larger buffer for better performance
                    int bytesRead;
                    while ((bytesRead = fileIn.read(buffer)) != -1) {
                        dataOut.write(buffer, 0, bytesRead);
                    }
                }

                // Read transfer complete response
                FtpResponse completeResponse = readResponse(reader);
                if (!completeResponse.isPositiveCompletion()) {
                    log.error("File transfer not successful: {}", completeResponse);
                    throw new FtpUploaderException(
                            "File transfer not successful: " + completeResponse,
                            "Trasferimento file non riuscito: " + completeResponse
                    );
                }

                log.debug("File upload successful: {}", fileName);
            } finally {
                try {
                    if (dataSocket != null && !dataSocket.isClosed()) {
                        dataSocket.close();
                    }
                } catch (IOException e) {
                    log.warn("Error closing data socket", e);
                }
            }
        } catch (IOException e) {
            log.error("I/O error during FTP upload", e);
            throw new FtpUploaderException(
                    "I/O error during FTP upload: " + e.getMessage(),
                    "Errore di I/O durante il caricamento FTP: " + e.getMessage(),
                    e
            );
        } finally {
            try {
                if (controlSocket != null && !controlSocket.isClosed()) {
                    // Try to send QUIT command
                    try {
                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(controlSocket.getOutputStream(), StandardCharsets.UTF_8));
                        sendCommand(writer, "QUIT");
                    } catch (IOException ignored) {
                        // Ignore errors during cleanup
                    }
                    controlSocket.close();
                }
            } catch (IOException e) {
                log.warn("Error closing control socket", e);
            }
        }
    }

    private static void doLogin(BufferedReader reader, BufferedWriter writer,
                                String username, String password) throws IOException, FtpUploaderException {
        // Send username
        sendCommand(writer, "USER " + username);
        FtpResponse userResponse = readResponse(reader);
        if (userResponse.isPositiveCompletion()) {
            return; // Some servers don't require passwords
        }

        if (!userResponse.isPositiveIntermediate()) {
            log.error("Username rejected: {}", userResponse);
            throw new FtpUploaderException(
                    "Username rejected: " + userResponse,
                    "Nome utente rifiutato: " + userResponse
            );
        }

        // Send password
        sendCommand(writer, "PASS " + password);
        FtpResponse passResponse = readResponse(reader);
        if (!passResponse.isPositiveCompletion()) {
            log.error("Authentication failed: {}", passResponse);
            throw new FtpUploaderException(
                    "Authentication failed: " + passResponse,
                    "Autenticazione fallita: " + passResponse
            );
        }
    }

    private static void ensureDirectoryExists(BufferedReader reader, BufferedWriter writer,
                                              String remotePath) throws IOException, FtpUploaderException {
        if (remotePath == null || remotePath.isEmpty() || remotePath.equals("/")) {
            return;
        }

        // Try to change to the directory first to see if it exists
        sendCommand(writer, "CWD " + remotePath);
        FtpResponse cwdResponse = readResponse(reader);
        if (cwdResponse.isPositiveCompletion()) {
            return; // Directory exists
        }

        // Directory doesn't exist, create it
        String[] dirs = remotePath.split("/");
        StringBuilder currentPath = new StringBuilder();

        for (String dir : dirs) {
            if (dir.isEmpty()) continue;

            currentPath.append("/").append(dir);

            // Try to change to this directory
            sendCommand(writer, "CWD " + currentPath);
            FtpResponse response = readResponse(reader);

            if (!response.isPositiveCompletion()) {
                // Directory doesn't exist, create it
                sendCommand(writer, "MKD " + currentPath);
                FtpResponse mkdResponse = readResponse(reader);

                if (!mkdResponse.isPositiveCompletion()) {
                    log.error("Failed to create directory {}: {}", currentPath, mkdResponse);
                    throw new FtpUploaderException(
                            "Failed to create directory " + currentPath + ": " + mkdResponse,
                            "Impossibile creare la directory " + currentPath + ": " + mkdResponse
                    );
                }

                // Change to the newly created directory
                sendCommand(writer, "CWD " + currentPath);
                readResponse(reader); // Read but ignore as we just created it
            }
        }

        // Go back to root directory to ensure consistent state
        sendCommand(writer, "CWD /");
        readResponse(reader);
    }

    private static Socket initiatePassiveMode(BufferedReader reader, BufferedWriter writer) throws IOException, FtpUploaderException {
        sendCommand(writer, "PASV");
        FtpResponse pasvResponse = readResponse(reader);

        if (!pasvResponse.isPositiveCompletion()) {
            log.error("Failed to enter passive mode: {}", pasvResponse);
            throw new FtpUploaderException(
                    "Failed to enter passive mode: " + pasvResponse,
                    "Impossibile entrare in modalità passiva: " + pasvResponse
            );
        }

        // Extract IP and port from the PASV response
        Matcher matcher = PASV_PATTERN.matcher(pasvResponse.getMessage());
        if (!matcher.find()) {
            log.error("Invalid PASV response format: {}", pasvResponse.getMessage());
            throw new FtpUploaderException(
                    "Invalid PASV response format: " + pasvResponse.getMessage(),
                    "Formato di risposta PASV non valido: " + pasvResponse.getMessage()
            );
        }

        String hostIP = matcher.group(1) + "." + matcher.group(2) + "." +
                matcher.group(3) + "." + matcher.group(4);
        int port = (Integer.parseInt(matcher.group(5)) << 8) + Integer.parseInt(matcher.group(6));

        // Connect to the data socket
        Socket dataSocket = new Socket();
        try {
            dataSocket.connect(new InetSocketAddress(hostIP, port), DEFAULT_TIMEOUT);
            dataSocket.setSoTimeout(DEFAULT_TIMEOUT);
            return dataSocket;
        } catch (IOException e) {
            log.error(String.format("Failed to connect to data port (%s:%s)", hostIP, port), e);
            dataSocket.close();
            throw new FtpUploaderException(
                    "Failed to connect to data port (" + hostIP + ":" + port + "): " + e.getMessage(),
                    "Impossibile connettersi alla porta dati (" + hostIP + ":" + port + "): " + e.getMessage(),
                    e
            );
        }
    }

    private static void sendCommand(BufferedWriter writer, String command) throws IOException {
        String logCommand = command;
        // Mask password for logging
        if (command.startsWith("PASS ")) {
            logCommand = "PASS ********";
        }
        log.debug(">> {}", logCommand);
        writer.write(command + "\r\n");
        writer.flush();
    }

    private static FtpResponse readResponse(BufferedReader reader) throws IOException {
        List<String> responseLines = new ArrayList<>();
        String line;
        String firstLine = null;
        int code = 0;

        while ((line = reader.readLine()) != null) {
            log.debug("<< {}", line);

            if (line.length() < 3) {
                continue; // Invalid line
            }

            if (firstLine == null) {
                firstLine = line;
                try {
                    code = Integer.parseInt(line.substring(0, 3));
                } catch (NumberFormatException e) {
                    log.warn("Invalid response code format: {}", line);
                }
            }

            responseLines.add(line);

            // Check if this is the last line of the response
            if (line.length() >= 4 && line.charAt(3) == ' ') {
                break;
            }
        }

        return new FtpResponse(code, String.join("\n", responseLines));
    }

    public static String sanitizeFileName(String fileName) {
        // Remove any path components that might be in the filename
        if (fileName.contains("/") || fileName.contains("\\")) {
            fileName = new File(fileName).getName();
        }

        // Further sanitize to prevent other potential issues
        return fileName.replaceAll("[^a-zA-Z0-9._-]", "_");
    }

    /**
     * Represents an FTP server response
     */
    private static class FtpResponse {
        private final int code;
        private final String message;

        public FtpResponse(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public boolean isPositiveCompletion() {
            return code >= 200 && code < 300;
        }

        public boolean isPositiveIntermediate() {
            return code >= 300 && code < 400;
        }

        public boolean isPositivePreliminary() {
            return code >= 100 && code < 200;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return code + " " + message;
        }
    }
}