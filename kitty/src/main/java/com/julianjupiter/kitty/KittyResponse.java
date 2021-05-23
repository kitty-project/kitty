package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Body;
import com.julianjupiter.kitty.http.message.HttpHeader;
import com.julianjupiter.kitty.http.message.HttpHeaders;
import com.julianjupiter.kitty.http.message.HttpStatus;
import com.julianjupiter.kitty.http.message.HttpVersion;
import com.julianjupiter.kitty.http.message.Response;
import com.julianjupiter.kitty.http.message.StatusLine;

/**
 * @author Julian Jupiter
 */
final class KittyResponse extends KittyMessage implements Response {
    private StatusLine statusLine = new KittyStatusLine(HttpStatus.OK);

    @Override
    public HttpVersion version() {
        return this.statusLine.version();
    }

    @Override
    public HttpStatus status() {
        return this.statusLine.status();
    }

    @Override
    public Response status(int statusCode) {
        this.statusLine = new KittyStatusLine(HttpStatus.of(statusCode), this.statusLine.version());
        return this;
    }

    @Override
    public Response status(HttpStatus status) {
        this.statusLine = new KittyStatusLine(status, this.statusLine.version());
        return this;
    }

    @Override
    public StatusLine statusLine() {
        return this.statusLine;
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
    public Response body(Body body) {
        return null;
    }
}