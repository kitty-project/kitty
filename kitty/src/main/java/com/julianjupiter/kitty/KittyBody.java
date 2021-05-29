package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Body;

/**
 * @author Julian Jupiter
 */
final class HttpBody implements Body {
    @Override
    public boolean isReadable() {
        return false;
    }

    @Override
    public boolean isWritable() {
        return false;
    }

    @Override
    public int contentLength() {
        return 0;
    }
}
