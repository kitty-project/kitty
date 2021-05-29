package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpStatus;
import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.Response;

/**
 * @author Julian Jupiter
 */
final record KittyResponseCreator(Request request) implements ResponseCreator {
    @Override
    public Response createKittyResponse() {
        return new KittyResponse(new KittyStatusLine(HttpStatus.OK, this.request.version()));
    }
}
