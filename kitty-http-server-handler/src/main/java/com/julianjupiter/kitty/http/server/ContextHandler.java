package com.julianjupiter.kitty.http.server;

import com.julianjupiter.kitty.http.message.Context;
import com.julianjupiter.kitty.http.message.Response;

/**
 * @author Julian Jupiter
 */
@FunctionalInterface
public interface ContextHandler {

    Response handle(Context ctx);

}
