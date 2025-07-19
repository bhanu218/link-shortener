package com.linkshortener.link_shortener.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortenResponse {
    private String shortUrl;
    private String originalUrl;
    private String expiresAt;
}
