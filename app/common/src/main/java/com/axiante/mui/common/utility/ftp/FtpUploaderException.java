package com.axiante.mui.common.utility.ftp;


import lombok.Getter;

/**
 * Custom exception for FTP upload operations
 */
@Getter
public class FtpUploaderException extends Exception {
    private static final long serialVersionUID = -6063691069561492079L;
    private final String italianMessage;

    public FtpUploaderException(String message, String italianMessage) {
        super(message);
        this.italianMessage = italianMessage;
    }

    public FtpUploaderException(String message, String italianMessage, Throwable cause) {
        super(message, cause);
        this.italianMessage = italianMessage;
    }

}
