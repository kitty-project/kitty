package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpMethod;

/**
 * @author Julian Jupiter
 */
public sealed interface Route permits KittyHttpMethodNotAllowedRoute, KittyNotFoundRoute, KittyRoute {
    HttpMethod method();

    String path();

    Handler handler();
}
