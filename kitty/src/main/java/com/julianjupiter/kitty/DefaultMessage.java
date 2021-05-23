package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Body;
import com.julianjupiter.kitty.http.message.HttpHeaders;
import com.julianjupiter.kitty.http.message.Message;

/**
 * @author Julian Jupiter
 */
abstract class DefaultMessage implements Message {

    @Override
    public HttpHeaders headers() {
        return null;
    }

    @Override
    public Body body() {
        return null;
    }
}
