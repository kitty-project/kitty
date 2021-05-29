package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Body;
import com.julianjupiter.kitty.http.message.Cookie;
import com.julianjupiter.kitty.http.message.QueryParam;
import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.RequestLine;
import com.julianjupiter.kitty.http.message.util.HttpMethod;
import com.julianjupiter.kitty.http.message.util.HttpVersion;

import java.net.URI;

/**
 * @author Julian Jupiter
 */
final class KittyRequest extends KittyMessage implements Request {
    private final RequestLine requestLine;
    private final QueryParam[] queryParams;

    KittyRequest(RequestLine requestLine, QueryParam[] queryParams, KittyHttpHeaders httpHeaders, KittyHttpCookies kittyHttpCookies, Body body) {
        super(httpHeaders, kittyHttpCookies, body);
        this.requestLine = requestLine;
        this.queryParams = queryParams;
    }

    @Override
    public HttpVersion version() {
        return this.requestLine.version();
    }

    @Override
    public HttpMethod method() {
        return this.requestLine.method();
    }

    @Override
    public URI target() {
        return this.requestLine.target();
    }

    @Override
    public RequestLine requestLine() {
        return this.requestLine;
    }

    @Override
    public QueryParam[] queryParams() {
        return this.queryParams;
    }
}
