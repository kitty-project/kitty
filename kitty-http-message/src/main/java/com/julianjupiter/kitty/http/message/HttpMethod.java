package com.julianjupiter.kitty.http.message;

import java.util.Arrays;

/**
 * @author Julian Jupiter
 */
public enum HttpMethod {

    CONNECT, DELETE, GET, HEAD, OPTIONS, PATCH, POST, PUT, TRACE;

    public static HttpMethod of(String method) {
        if (method == null || method.isBlank()) {
            throw new IllegalArgumentException("HTTP method must not be null or empty");
        }

        return Arrays.stream(values())
                .filter(httpMethod -> httpMethod.name().equalsIgnoreCase(method))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid HTTP Method " + method));
    }

    @Override
    public String toString() {
        return name();
    }
}