package com.vladyslavnicko.gmail.exception;

import com.vladyslavnicko.gmail.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConflictException extends RuntimeException {
    private String message;

    public ConflictException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConflictException(Throwable cause) {
        super(cause);
    }
}
