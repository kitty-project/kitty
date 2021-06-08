package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Body;
import com.julianjupiter.kitty.http.message.QueryParam;
import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.util.HttpMethod;

import java.net.URI;
import java.util.Arrays;

/**
 * @author Julian Jupiter
 */
final class KittyRequest extends KittyMessage implements Request {
    private final HttpMethod method;
    private final URI target;
    private final QueryParam[] queryParams;

    KittyRequest(RequestLine requestLine, QueryParam[] queryParams, KittyHttpHeaders httpHeaders, KittyHttpCookies kittyHttpCookies, Body body) {
        super(requestLine.version(), httpHeaders, kittyHttpCookies, body);
        this.method = requestLine.method();
        this.target = requestLine.target();
        this.queryParams = queryParams;
    }

    @Override
    public HttpMethod method() {
        return this.method;
    }

    @Override
    public URI target() {
        return this.target;
    }

    @Override
    public QueryParam queryParam(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }

        return Arrays.stream(this.queryParams)
                .filter(queryParam -> queryParam.name().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public QueryParam[] queryParams() {
        return this.queryParams;
    }
}
