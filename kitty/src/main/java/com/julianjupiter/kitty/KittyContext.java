package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Context;
import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.Response;

/**
 * @author Julian Jupiter
 */
record KittyContext(Request request, Response response) implements Context {
}
