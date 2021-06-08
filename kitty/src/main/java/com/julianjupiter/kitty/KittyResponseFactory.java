package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpStatus;
import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.Response;

/**
 * @author Julian Jupiter
 */
final class KittyResponseFactory implements ResponseFactory {
    @Override
    public Response createResponse(Request request) {
        return new KittyResponse(new KittyStatusLine(HttpStatus.OK, request.version()));
    }
}
