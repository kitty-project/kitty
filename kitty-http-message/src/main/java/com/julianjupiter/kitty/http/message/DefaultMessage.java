package com.julianjupiter.kitty.http.message;

/**
 * @author Julian Jupiter
 */
class DefaultMessage implements Message {
    @Override
    public HttpVersion version() {
        return null;
    }

    @Override
    public HttpHeaders headers() {
        return null;
    }

    @Override
    public HttpBody body() {
        return null;
    }
}
