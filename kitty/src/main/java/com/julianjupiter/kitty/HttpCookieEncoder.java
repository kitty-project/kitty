package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Header;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Julian Jupiter
 */
class HttpCookieEncoder {
    private HttpCookieEncoder() {
    }

    public static String encode(List<Header> headers) {
        return headers.stream()
                .map(Header::value)
                .collect(Collectors.joining("; "));
    }
}
