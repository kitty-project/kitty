package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.server.RequestHandler;

/**
 * @author Julian Jupiter
 */
public interface RouterProxy {
    Kitty get(String path, RequestHandler handler);
}
