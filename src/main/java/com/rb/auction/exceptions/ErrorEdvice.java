package com.rb.auction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorEdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityDoNotExistsException.class)
    public ErrorResponse EntityDoNotExistsException(EntityDoNotExistsException e) {
        return ErrorResponse.builder()
                .message("Error text")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
