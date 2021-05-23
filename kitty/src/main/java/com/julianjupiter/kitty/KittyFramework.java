package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.HttpMethod;
import com.julianjupiter.kitty.http.server.ContextHandler;
import com.julianjupiter.kitty.http.server.RequestHandler;

import java.util.Set;

/**
 * @author Julian Jupiter
 */
final class KittyFramework implements Kitty {
    private final Configuration configuration;
    private final Router router;

    KittyFramework() {
        this(Configuration.create(), Router.create());
    }

    KittyFramework(Configuration configuration) {
        this(configuration, Router.create());
    }

    KittyFramework(Router router) {
        this(Configuration.create(), router);
    }

    KittyFramework(Configuration configuration, Router router) {
        this.configuration = configuration;
        this.router = router;
    }

    @Override
    public void run() {

    }

    @Override
    public void run(int port) {

    }

    @Override
    public void run(Runnable runnable) {
        runnable.run();
    }

    @Override
    public void run(int port, Runnable runnable) {
        runnable.run();
    }

    @Override
    public Kitty any(String path, RequestHandler handler) {
        this.router.any(path, handler);
        return this;
    }

    @Override
    public Kitty any(String path, ContextHandler handler) {
        this.router.any(path, handler);
        return this;
    }

    @Override
    public Kitty anyOf(Set<HttpMethod> methods, String path, RequestHandler handler) {
        this.router.anyOf(methods, path, handler);
        return this;
    }

    @Override
    public Kitty anyOf(Set<HttpMethod> methods, String path, ContextHandler handler) {
        this.router.anyOf(methods, path, handler);
        return this;
    }

    @Override
    public Kitty delete(String path, RequestHandler handler) {
        this.router.delete(path, handler);
        return this;
    }

    @Override
    public Kitty delete(String path, ContextHandler handler) {
        this.router.delete(path, handler);
        return this;
    }

    @Override
    public Kitty get(String path, RequestHandler handler) {
        this.router.get(path, handler);
        return this;
    }

    @Override
    public Kitty get(String path, ContextHandler handler) {
        this.router.get(path, handler);
        return this;
    }

    @Override
    public Kitty head(String path, RequestHandler handler) {
        this.router.any(path, handler);
        return this;
    }

    @Override
    public Kitty head(String path, ContextHandler handler) {
        this.router.head(path, handler);
        return this;
    }

    @Override
    public Kitty options(String path, RequestHandler handler) {
        this.router.options(path, handler);
        return this;
    }

    @Override
    public Kitty options(String path, ContextHandler handler) {
        this.router.options(path, handler);
        return this;
    }

    @Override
    public Kitty patch(String path, RequestHandler handler) {
        this.router.patch(path, handler);
        return this;
    }

    @Override
    public Kitty patch(String path, ContextHandler handler) {
        this.router.patch(path, handler);
        return this;
    }

    @Override
    public Kitty post(String path, RequestHandler handler) {
        this.router.post(path, handler);
        return this;
    }

    @Override
    public Kitty post(String path, ContextHandler handler) {
        this.router.post(path, handler);
        return this;
    }

    @Override
    public Kitty put(String path, RequestHandler handler) {
        this.router.put(path, handler);
        return this;
    }

    @Override
    public Kitty put(String path, ContextHandler handler) {
        this.router.put(path, handler);
        return this;
    }

    @Override
    public Kitty trace(String path, RequestHandler handler) {
        this.router.trace(path, handler);
        return this;
    }

    @Override
    public Kitty trace(String path, ContextHandler handler) {
        this.router.trace(path, handler);
        return this;
    }
}
