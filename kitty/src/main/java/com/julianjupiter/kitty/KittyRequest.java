package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Body;
import com.julianjupiter.kitty.http.message.PathParam;
import com.julianjupiter.kitty.http.message.QueryParam;
import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.util.HttpMethod;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Julian Jupiter
 */
final class KittyRequest extends KittyMessage implements Request {
    private final HttpMethod method;
    private final URI target;
    private final QueryParam[] queryParams;
    private final PathParam[] pathParams;

    KittyRequest(RequestLine requestLine, QueryParam[] queryParams, KittyHttpHeaders kittyHttpHeaders, KittyHttpCookies kittyHttpCookies, Body body) {
        this(requestLine, queryParams, kittyHttpHeaders, kittyHttpCookies, body, new PathParam[0]);
    }

    KittyRequest(RequestLine requestLine, QueryParam[] queryParams, KittyHttpHeaders kittyHttpHeaders, KittyHttpCookies kittyHttpCookies, Body body, PathParam[] pathParams) {
        super(requestLine.version(), kittyHttpHeaders, kittyHttpCookies, body);
        this.method = requestLine.method();
        this.target = requestLine.target();
        this.queryParams = queryParams;
        this.pathParams = pathParams;
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
    public Optional<QueryParam> queryParam(String name) {
        if (name == null || name.isBlank()) {
            return Optional.empty();
        }

        return Arrays.stream(this.queryParams)
                .filter(queryParam -> queryParam.name().equals(name))
                .findFirst();
    }

    @Override
    public QueryParam[] queryParams() {
        return this.queryParams;
    }

    @Override
    public Optional<PathParam> pathParam(String name) {
        if (name == null || name.isBlank()) {
            return Optional.empty();
        }

        return Arrays.stream(this.pathParams)
                .filter(pathParam -> pathParam.name().equals(name))
                .findFirst();
    }

    @Override
    public PathParam[] pathParams() {
        return this.pathParams;
    }
}
