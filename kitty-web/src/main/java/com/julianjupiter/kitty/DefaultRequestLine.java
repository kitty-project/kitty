package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.HttpMethod;
import com.julianjupiter.kitty.http.message.HttpVersion;
import com.julianjupiter.kitty.http.message.RequestLine;

import java.net.URI;
import java.util.Objects;

/**
 * @author Julian Jupiter
 */
record DefaultRequestLine(HttpMethod method, URI target, HttpVersion version) implements RequestLine {
    DefaultRequestLine(HttpMethod method, URI target) {
        this(method, target, null);
    }

    DefaultRequestLine(HttpMethod method, URI target, HttpVersion version) {
        this.method = Objects.requireNonNullElseGet(method, () -> HttpMethod.GET);
        this.target = Objects.requireNonNull(target, "Request target must not be null");
        this.version = Objects.requireNonNullElse(version, HttpVersion.HTTP_1_1);
    }

    @Override
    public String toString() {
        return method.name() + " " + target.toString() + " " + version.toString();
    }
}
