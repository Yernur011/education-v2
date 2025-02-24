package com.rdlab.education.domain.dto.error;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ErrorResponse implements Serializable {
    Integer statusCode;
    String message;
    String details;
    String timestamp;

    public ErrorResponse(int statusCode, String message, String details) {
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now().toString();
    }
}


