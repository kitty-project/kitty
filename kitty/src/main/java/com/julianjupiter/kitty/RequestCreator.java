package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Request;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @author Julian Jupiter
 */
sealed interface RequestCreator permits KittyRequestCreator {
    static RequestCreator create() {
        return new KittyRequestCreator();
    }

    RequestCreator nettyHttpRequest(HttpRequest httpRequest);

    RequestCreator nettyHttpContent(HttpContent httpContent);

    Request createKittyRequest();
}
