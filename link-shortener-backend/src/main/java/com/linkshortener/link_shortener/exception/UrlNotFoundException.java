package com.linkshortener.link_shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String msg) { super(msg); }
}