package com.linkshortener.link_shortener.exception;

import com.linkshortener.link_shortener.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<ErrorResponse> handleInvalid(InvalidUrlException ex) {
        log.error("Exception Occurred due to Invalid Url", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        System.currentTimeMillis()
                ));
    }

    @ExceptionHandler(UrlExpiredException.class)
    public ResponseEntity<ErrorResponse> handleExpired(UrlExpiredException ex) {
        log.error("URL expired: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.GONE)
                .body(new ErrorResponse(
                        "This short link has expired.",
                        HttpStatus.GONE.value(),
                        System.currentTimeMillis()
                ));
    }

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(UrlNotFoundException ex) {
        log.error("URL not found: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        "This Url short link has not Found.",
                        HttpStatus.NOT_FOUND.value(),
                        System.currentTimeMillis()
                ));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalidUrl(IllegalArgumentException ex) {
        log.error("Exception Occurred due to Url Invalid Format", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid URL format");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        log.error("Exception Occurred ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }
}

