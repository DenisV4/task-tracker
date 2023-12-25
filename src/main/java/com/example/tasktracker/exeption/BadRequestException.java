package com.example.tasktracker.exeption;

import java.text.MessageFormat;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Object... args) {
        this(MessageFormat.format(message, args));
    }
}
