package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.HttpMethod;

/**
 * @author Julian Jupiter
 */
public sealed interface Route permits KittyRoute {
    HttpMethod method();

    PathPattern path();

    Handler handler();
}
