package com.julianjupiter.kitty.http.message;

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
