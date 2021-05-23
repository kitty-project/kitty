package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.HttpMethod;
import com.julianjupiter.kitty.http.server.ContextHandler;
import com.julianjupiter.kitty.http.server.RequestHandler;

import java.util.Set;

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

    @Override
    public Kitty any(String path, RequestHandler handler) {
        return this;
    }

    @Override
    public Kitty any(String path, ContextHandler handler) {
        return this;
    }

    @Override
    public Kitty anyOf(Set<HttpMethod> methods, String path, RequestHandler handler) {
        return this;
    }

    @Override
    public Kitty anyOf(Set<HttpMethod> methods, String path, ContextHandler handler) {
        return this;
    }

    @Override
    public Kitty delete(String path, RequestHandler handler) {
        return this;
    }

    @Override
    public Kitty delete(String path, ContextHandler handler) {
        return this;
    }

    @Override
    public Kitty head(String path, RequestHandler handler) {
        return this;
    }

    @Override
    public Kitty head(String path, ContextHandler handler) {
        return this;
    }

    @Override
    public Kitty options(String path, RequestHandler handler) {
        return this;
    }

    @Override
    public Kitty options(String path, ContextHandler handler) {
        return this;
    }

    @Override
    public Kitty patch(String path, RequestHandler handler) {
        return this;
    }

    @Override
    public Kitty patch(String path, ContextHandler handler) {
        return this;
    }

    @Override
    public Kitty post(String path, RequestHandler handler) {
        return this;
    }

    @Override
    public Kitty post(String path, ContextHandler handler) {
        return this;
    }

    @Override
    public Kitty put(String path, RequestHandler handler) {
        return this;
    }

    @Override
    public Kitty put(String path, ContextHandler handler) {
        return this;
    }

    @Override
    public Kitty trace(String path, RequestHandler handler) {
        return this;
    }

    @Override
    public Kitty trace(String path, ContextHandler handler) {
        return this;
    }
}
