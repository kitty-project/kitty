package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.Response;

/**
 * @author Julian Jupiter
 */
sealed interface ResponseCreator permits KittyResponseCreator {
    static ResponseCreator create(Request request) {
        return new KittyResponseCreator(request);
    }

    Response createKittyResponse();
}
