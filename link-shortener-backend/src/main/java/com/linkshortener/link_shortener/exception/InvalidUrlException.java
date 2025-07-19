package com.linkshortener.link_shortener.exception;

public class InvalidUrlException extends RuntimeException {
    public InvalidUrlException(String msg) { super(msg); }
}