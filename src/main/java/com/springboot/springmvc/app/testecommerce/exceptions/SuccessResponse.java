package com.springboot.springmvc.app.testecommerce.exceptions;

import java.time.LocalDateTime;

public class SuccessResponse {
    private String code;
    private String message;
    private LocalDateTime timestamp;

    public SuccessResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public String getCode() { return code; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
}