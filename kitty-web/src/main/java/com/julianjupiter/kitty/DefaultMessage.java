package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.HttpBody;
import com.julianjupiter.kitty.http.message.HttpHeaders;
import com.julianjupiter.kitty.http.message.HttpVersion;
import com.julianjupiter.kitty.http.message.Message;

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