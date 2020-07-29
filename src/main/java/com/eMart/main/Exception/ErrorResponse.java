package com.eMart.main.Exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ErrorResponse {
    private final String message;
    private final LocalDateTime timestamp;
    private final HttpStatus error;

    public ErrorResponse(String message,  HttpStatus error, LocalDateTime timestamp) {
        this.message = message;

        this.timestamp = timestamp;
        this.error = error;
    }
}
