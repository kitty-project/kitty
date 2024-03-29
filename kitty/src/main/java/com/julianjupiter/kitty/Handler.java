package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.Response;

/**
 * @author Julian Jupiter
 */
@FunctionalInterface
public interface Handler {
    Response handle(Request request, Response response);
}
