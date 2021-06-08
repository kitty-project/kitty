package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Header;
import com.julianjupiter.kitty.http.message.util.RequestHeaderName;
import io.netty.handler.codec.http.HttpRequest;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Julian Jupiter
 */
class HttpHeadersFactory {
    private HttpHeadersFactory() {
    }

    public static HttpHeadersFactory create() {
        return new HttpHeadersFactory();
    }

    public Header[] createHeaders(HttpRequest request) {
        return request.headers().entries().stream()
                .filter(Predicate.not(httpHeader -> httpHeader.getKey().equalsIgnoreCase(RequestHeaderName.COOKIE.toString())))
                .map(httpHeader -> new KittyHeader(httpHeader.getKey(), httpHeader.getValue()))
                .toList()
                .toArray(new KittyHeader[0]);
    }
}
