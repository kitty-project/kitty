package com.julianjupiter.kitty.http.server;

import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.Response;

/**
 * @author Julian Jupiter
 */
@FunctionalInterface
public interface RequestHandler {
    Response handle(Request request, Response response);
}
