package com.julianjupiter.kitty.http.message;

import com.julianjupiter.kitty.http.message.HttpBody;
import com.julianjupiter.kitty.http.message.HttpHeaders;
import com.julianjupiter.kitty.http.message.Message;
import com.julianjupiter.kitty.http.message.ProtocolVersion;

/**
 * @author Julian Jupiter
 */
class DefaultMessage implements Message {
    @Override
    public ProtocolVersion version() {
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
