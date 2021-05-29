package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpMethod;
import com.julianjupiter.kitty.http.message.util.HttpVersion;
import com.julianjupiter.kitty.http.message.RequestLine;

import java.net.URI;
import java.util.Objects;

/**
 * @author Julian Jupiter
 */
record KittyRequestLine(HttpMethod method, URI target, HttpVersion version) implements RequestLine {
    KittyRequestLine(HttpMethod method, URI target) {
        this(method, target, null);
    }

    KittyRequestLine(HttpMethod method, URI target, HttpVersion version) {
        this.method = Objects.requireNonNullElse(method, HttpMethod.GET);
        this.target = Objects.requireNonNull(target, "Request target must not be null");
        this.version = Objects.requireNonNullElse(version, HttpVersion.HTTP_1_1);
    }

    @Override
    public String toString() {
        return this.method.name() + " " + this.target.toString() + " " + this.version.toString();
    }
}
