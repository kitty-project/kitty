package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.HttpBody;
import com.julianjupiter.kitty.http.message.HttpHeader;
import com.julianjupiter.kitty.http.message.HttpHeaders;
import com.julianjupiter.kitty.http.message.HttpStatus;
import com.julianjupiter.kitty.http.message.HttpVersion;
import com.julianjupiter.kitty.http.message.Response;

/**
 * @author Julian Jupiter
 */
final class DefaultResponse extends DefaultMessage implements Response {
    @Override
    public HttpVersion version() {
        return null;
    }

    @Override
    public HttpStatus status() {
        return null;
    }

    @Override
    public Response status(int statusCode) {
        return null;
    }

    @Override
    public Response status(HttpStatus status) {
        return null;
    }

    @Override
    public Response header(String name, String value) {
        return null;
    }

    @Override
    public Response header(HttpHeader header) {
        return null;
    }

    @Override
    public Response headers(HttpHeaders httpHeaders) {
        return null;
    }

    @Override
    public Response body(HttpBody body) {
        return null;
    }
}
