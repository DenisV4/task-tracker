package com.example.tasktracker.exeption;

import java.text.MessageFormat;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Object... args) {
        this(MessageFormat.format(message, args));
    }
}
