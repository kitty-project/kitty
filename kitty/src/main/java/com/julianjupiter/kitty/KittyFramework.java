package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.server.ContextHandler;
import com.julianjupiter.kitty.http.server.RequestHandler;

/**
 * @author Julian Jupiter
 */
final class KittyFramework implements Kitty {

    @Override
    public Kitty get(String path, ContextHandler handler) {
        return this;
    }

    @Override
    public Kitty get(String path, RequestHandler handler) {
        return this;
    }

    @Override
    public void run() {

    }

    @Override
    public void run(Runnable runnable) {
        runnable.run();
    }
}
