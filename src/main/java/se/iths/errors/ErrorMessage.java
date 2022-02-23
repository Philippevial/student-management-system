package se.iths.errors;

import java.time.LocalDateTime;

public class ErrorMessage {

    LocalDateTime localDateTime = LocalDateTime.now();
    String errorCode;
    String message;
    String url;

    public ErrorMessage(String errorCode, String message, String url) {
        this.errorCode = errorCode;
        this.message = message;
        this.url = url;
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
