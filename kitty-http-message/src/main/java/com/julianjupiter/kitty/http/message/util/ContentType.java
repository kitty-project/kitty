package com.julianjupiter.kitty.http.message.util;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Julian Jupiter
 */
public enum ContentType {
    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    TEXT_HTML("text/html"),
    TEXT_PLAIN("text/plain"),
    UNKNOWN("unknown");

    private final String value;

    ContentType(String value) {
        this.value = value;
    }

    public static ContentType of(String contentType) {
        Objects.requireNonNull(contentType, "contentType cannot be null");

        return Arrays.stream(values())
                .filter(ct -> ct.value.equals(contentType))
                .findFirst()
                .orElse(ContentType.UNKNOWN);
    }

    @Override
    public String toString() {
        return value;
    }
}

