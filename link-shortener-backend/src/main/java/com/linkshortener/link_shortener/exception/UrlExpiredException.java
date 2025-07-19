package com.linkshortener.link_shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UrlExpiredException extends RuntimeException {
    public UrlExpiredException(String msg) { super(msg); }
}
