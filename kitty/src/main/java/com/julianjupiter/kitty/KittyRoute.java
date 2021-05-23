package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.HttpMethod;

/**
 * @author Julian Jupiter
 */
public final record KittyRoute(HttpMethod method, PathPattern path, Handler handler) implements Route {
}
