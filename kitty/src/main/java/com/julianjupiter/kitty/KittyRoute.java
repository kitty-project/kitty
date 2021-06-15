package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpMethod;

/**
 * @author Julian Jupiter
 */
final record KittyRoute(HttpMethod method, String path, Handler handler) implements Route {
    public static String x = "";
}
