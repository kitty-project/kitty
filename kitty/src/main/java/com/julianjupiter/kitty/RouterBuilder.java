package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpMethod;
import com.julianjupiter.kitty.http.server.ContextHandler;
import com.julianjupiter.kitty.http.server.RequestHandler;

import java.util.Set;

/**
 * @author Julian Jupiter
 */
public final class RouterBuilder implements RouterProxy<RouterBuilder>, Builder<Router> {
    private final Router router;

    private RouterBuilder() {
        this(new KittyRouter());
    }

    private RouterBuilder(Router router) {
        this.router = router;
    }

    static RouterBuilder builder(Router router) {
        return new RouterBuilder(router);
    }

    public static RouterBuilder builder() {
        return new RouterBuilder();
    }

    @Override
    public RouterBuilder any(String path, RequestHandler handler) {
        this.router.any(path, handler);
        return this;
    }

    @Override
    public RouterBuilder any(String path, ContextHandler handler) {
        this.router.any(path, handler);
        return this;
    }

    @Override
    public RouterBuilder anyOf(Set<HttpMethod> methods, String path, RequestHandler handler) {
        this.router.anyOf(methods, path, handler);
        return this;
    }

    @Override
    public RouterBuilder anyOf(Set<HttpMethod> methods, String path, ContextHandler handler) {
        this.router.anyOf(methods, path, handler);
        return this;
    }

    @Override
    public RouterBuilder delete(String path, RequestHandler handler) {
        this.router.delete(path, handler);
        return this;
    }

    @Override
    public RouterBuilder delete(String path, ContextHandler handler) {
        this.router.delete(path, handler);
        return this;
    }

    @Override
    public RouterBuilder get(String path, RequestHandler handler) {
        this.router.get(path, handler);
        return this;
    }

    @Override
    public RouterBuilder get(String path, ContextHandler handler) {
        this.router.get(path, handler);
        return this;
    }

    @Override
    public RouterBuilder head(String path, RequestHandler handler) {
        this.router.head(path, handler);
        return this;
    }

    @Override
    public RouterBuilder head(String path, ContextHandler handler) {
        this.router.head(path, handler);
        return this;
    }

    @Override
    public RouterBuilder options(String path, RequestHandler handler) {
        this.router.options(path, handler);
        return this;
    }

    @Override
    public RouterBuilder options(String path, ContextHandler handler) {
        this.router.options(path, handler);
        return this;
    }

    @Override
    public RouterBuilder patch(String path, RequestHandler handler) {
        this.router.patch(path, handler);
        return this;
    }

    @Override
    public RouterBuilder patch(String path, ContextHandler handler) {
        this.router.patch(path, handler);
        return this;
    }

    @Override
    public RouterBuilder post(String path, RequestHandler handler) {
        this.router.post(path, handler);
        return this;
    }

    @Override
    public RouterBuilder post(String path, ContextHandler handler) {
        this.router.post(path, handler);
        return this;
    }

    @Override
    public RouterBuilder put(String path, RequestHandler handler) {
        this.router.put(path, handler);
        return this;
    }

    @Override
    public RouterBuilder put(String path, ContextHandler handler) {
        this.router.put(path, handler);
        return this;
    }

    @Override
    public RouterBuilder trace(String path, RequestHandler handler) {
        this.router.trace(path, handler);
        return this;
    }

    @Override
    public RouterBuilder trace(String path, ContextHandler handler) {
        this.router.trace(path, handler);
        return this;
    }

    @Override
    public Router build() {
        return this.router;
    }
}
