package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.Response;

/**
 * @author Julian Jupiter
 */
sealed interface ResponseFactory permits KittyResponseFactory {
    static ResponseFactory create() {
        return new KittyResponseFactory();
    }

    Response createResponse(Request request);
}
