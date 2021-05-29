package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpMethod;
import com.julianjupiter.kitty.http.message.util.HttpStatus;

/**
 * @author Julian Jupiter
 */
final class KittyHttpMethodNotAllowedRoute implements Route {
    @Override
    public HttpMethod method() {
        return null;
    }

    @Override
    public String path() {
        return null;
    }

    @Override
    public Handler handler() {
        return ((request, response) -> response.status(HttpStatus.NOT_FOUND));
    }
}
