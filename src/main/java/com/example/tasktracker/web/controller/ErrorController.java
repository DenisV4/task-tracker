package com.example.tasktracker.web.controller;

import com.example.tasktracker.exeption.BadRequestException;
import com.example.tasktracker.exeption.ResourceNotFoundException;
import com.example.tasktracker.web.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleNotFoundException(ResourceNotFoundException exc) {
        var errorResponse = ErrorResponse.builder().errorMessage(exc.getMessage()).build();
        return Mono.just(
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleBadRequestException(BadRequestException exc) {
        var errorResponse = ErrorResponse.builder().errorMessage(exc.getMessage()).build();
        return Mono.just(
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
        );
    }
}
