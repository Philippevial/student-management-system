package se.iths.error;

import java.time.LocalDateTime;

public class ErrorMessage {

    LocalDateTime localDateTime = LocalDateTime.now();
    private final String errorCode;
    private final String message;
    private final String url;


    public ErrorMessage(String errorCode, String message, String url) {
        this.errorCode = errorCode;
        this.message = message;
        this.url = url;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }
}
