package com.magd.backend_bank.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp, String message, String status) {
    public ErrorDetails(String message, HttpStatus status) {
        this(LocalDateTime.now(), message, status.toString());
    }
}

