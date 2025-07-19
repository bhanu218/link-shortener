package com.linkshortener.link_shortener.mapper;


import com.linkshortener.link_shortener.dto.ShortenResponse;
import com.linkshortener.link_shortener.entity.ShortUrl;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlMapper {

    public ShortenResponse toDto(ShortUrl url, String baseUrl) {
        return ShortenResponse.builder()
                .shortUrl(baseUrl + "/" + url.getShortCode())
                .originalUrl(url.getOriginalUrl())
                .expiresAt(url.getExpiresAt().toString())
                .build();
    }
}
