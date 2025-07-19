package com.linkshortener.link_shortener.util;

public class UrlValidatorUtil {
    private static final String URL_REGEX =
            "^(https?://)?([\\w\\-])+\\.[\\w\\-]+([\\w\\-./?%&=]*)?$";

    public static boolean isValid(String url) {
        return url.matches(URL_REGEX);
    }
}

