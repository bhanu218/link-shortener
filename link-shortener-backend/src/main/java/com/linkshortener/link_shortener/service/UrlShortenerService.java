package com.linkshortener.link_shortener.service;


import com.linkshortener.link_shortener.dto.ShortenRequest;
import com.linkshortener.link_shortener.dto.ShortenResponse;

public interface UrlShortenerService {
    ShortenResponse shortenUrl(ShortenRequest request);
    ShortenResponse resolveUrlDto(String shortCode);
}

