package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.HttpMethod;
import com.julianjupiter.kitty.http.message.HttpVersion;
import com.julianjupiter.kitty.http.message.QueryParam;
import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.RequestLine;

import java.net.URI;

/**
 * @author Julian Jupiter
 */
final class KittyRequest extends KittyMessage implements Request {
    private final RequestLine requestLine;
    private final QueryParam[] queryParams;

    KittyRequest(RequestLine requestLine, QueryParam[] queryParams) {
        this.requestLine = requestLine;
        this.queryParams = queryParams;
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
    public HttpVersion version() {
        return this.requestLine.version();
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
