package com.linkshortener.link_shortener.service;

import com.linkshortener.link_shortener.dto.ShortenRequest;
import com.linkshortener.link_shortener.dto.ShortenResponse;
import com.linkshortener.link_shortener.entity.ShortUrl;
import com.linkshortener.link_shortener.exception.InvalidUrlException;
import com.linkshortener.link_shortener.exception.UrlExpiredException;
import com.linkshortener.link_shortener.exception.UrlNotFoundException;
import com.linkshortener.link_shortener.mapper.ShortUrlMapper;
import com.linkshortener.link_shortener.repository.ShortUrlRepository;
import com.linkshortener.link_shortener.util.Base62Generator;
import com.linkshortener.link_shortener.util.UrlValidatorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private final ShortUrlRepository repository;
    private final ShortUrlMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(UrlShortenerServiceImpl.class);

    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${frontend.base-url:http://localhost:3000}")
    private String frontendBaseUrl;

    @Override
    public ShortenResponse shortenUrl(ShortenRequest request) {
        String originalUrl = request.getOriginalUrl().trim();
        log.info("Entered shortenUrl() with input: {}", originalUrl);

        if (!UrlValidatorUtil.isValid(originalUrl)) {
            throw new InvalidUrlException("Invalid URL format.");
        }

        return repository.findByOriginalUrl(originalUrl).map(url -> {
            if (url.getExpiresAt().isAfter(Instant.now())) {
                return mapper.toDto(url, frontendBaseUrl);
            } else {
                url.setShortCode(Base62Generator.generateCode(8));
                url.setCreatedAt(Instant.now());
                url.setExpiresAt(Instant.now().plusSeconds(300)); // 5 minutes
                url.setLastAccessedAt(Instant.now());
                return mapper.toDto(repository.save(url), frontendBaseUrl);
            }
        }).orElseGet(() -> {
            ShortUrl newUrl = ShortUrl.builder()
                    .originalUrl(originalUrl)
                    .shortCode(Base62Generator.generateCode(8))
                    .createdAt(Instant.now())
                    .expiresAt(Instant.now().plusSeconds(300))
                    .lastAccessedAt(Instant.now())
                    .build();
            return mapper.toDto(repository.save(newUrl), frontendBaseUrl);
        });
    }

    @Override
    public ShortenResponse resolveUrlDto(String shortCode) {
        return repository.findByShortCode(shortCode).map(url -> {
            if (url.getExpiresAt().isBefore(Instant.now())) {
                throw new UrlExpiredException("This short link has expired.");
            }
            url.setLastAccessedAt(Instant.now());
            repository.save(url);
            return mapper.toDto(url, frontendBaseUrl);
        }).orElseThrow(() -> new UrlNotFoundException("Short link not found."));
    }


}
