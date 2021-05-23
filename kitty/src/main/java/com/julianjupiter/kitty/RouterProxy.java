package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.server.ContextHandler;
import com.julianjupiter.kitty.http.server.RequestHandler;

/**
 * @author Julian Jupiter
 */
public interface RouterProxy {

    Kitty get(String path, ContextHandler handler);

    Kitty get(String path, RequestHandler handler);

}
