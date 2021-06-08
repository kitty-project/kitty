package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Request;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @author Julian Jupiter
 */
sealed interface RequestFactory permits KittyRequestFactory {
    static RequestFactory create() {
        return new KittyRequestFactory();
    }

    Request createRequest(HttpRequest httpRequest, HttpContent httpContent);
}
