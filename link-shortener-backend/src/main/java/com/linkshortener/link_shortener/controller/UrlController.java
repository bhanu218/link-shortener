package com.linkshortener.link_shortener.controller;

import com.linkshortener.link_shortener.dto.ShortenRequest;
import com.linkshortener.link_shortener.dto.ShortenResponse;
import com.linkshortener.link_shortener.service.UrlShortenerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/url")
@AllArgsConstructor
public class UrlController {

    private final UrlShortenerService service;

    @PostMapping("/shorten")
    public ResponseEntity<ShortenResponse> shorten(@Valid @RequestBody ShortenRequest request) {
        return ResponseEntity.ok(service.shortenUrl(request));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<ShortenResponse> resolveUrl(@PathVariable String shortCode) {
        return ResponseEntity.ok(service.resolveUrlDto(shortCode));
    }

}
