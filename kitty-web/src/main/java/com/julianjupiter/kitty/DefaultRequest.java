package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.HttpMethod;
import com.julianjupiter.kitty.http.message.Request;

import java.net.URI;

/**
 * @author Julian Jupiter
 */
final class DefaultRequest extends DefaultMessage implements Request {
    @Override
    public HttpMethod method() {
        return null;
    }

    @Override
    public URI target() {
        return null;
    }
}
