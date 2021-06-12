package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpMethod;
import com.julianjupiter.kitty.http.server.ContextHandler;
import com.julianjupiter.kitty.http.server.RequestHandler;

import java.util.Set;

/**
 * @author Julian Jupiter
 */
public final class RouteGroupBuilder implements RouteGroupProxy<RouteGroupBuilder>, Builder<RouteGroup> {
    private final RouteGroup routeGroup;

    private RouteGroupBuilder() {
        this.routeGroup = new KittyRouteGroup();
    }

    public static RouteGroupBuilder create() {
        return new RouteGroupBuilder();
    }

    @Override
    public RouteGroupBuilder route(HttpMethod method, String path, RequestHandler handler) {
        this.routeGroup.route(method, path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder route(HttpMethod method, String path, ContextHandler handler) {
        this.routeGroup.route(method, path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder any(RequestHandler handler) {
        this.routeGroup.any(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder any(ContextHandler handler) {
        this.routeGroup.any(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder anyOf(Set<HttpMethod> methods, RequestHandler handler) {
        this.routeGroup.anyOf(methods, null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder anyOf(Set<HttpMethod> methods, ContextHandler handler) {
        this.routeGroup.anyOf(methods, null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder delete(RequestHandler handler) {
        this.routeGroup.delete(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder delete(ContextHandler handler) {
        this.routeGroup.delete(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder get(RequestHandler handler) {
        this.routeGroup.get(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder get(ContextHandler handler) {
        this.routeGroup.get(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder head(RequestHandler handler) {
        this.routeGroup.head(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder head(ContextHandler handler) {
        this.routeGroup.head(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder options(RequestHandler handler) {
        this.routeGroup.options(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder options(ContextHandler handler) {
        this.routeGroup.options(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder patch(RequestHandler handler) {
        this.routeGroup.patch(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder patch(ContextHandler handler) {
        this.routeGroup.patch(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder post(RequestHandler handler) {
        this.routeGroup.post(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder post(ContextHandler handler) {
        this.routeGroup.post(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder put(RequestHandler handler) {
        this.routeGroup.put(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder put(ContextHandler handler) {
        this.routeGroup.put(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder trace(RequestHandler handler) {
        this.routeGroup.trace(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder trace(ContextHandler handler) {
        this.routeGroup.trace(null, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder any(String path, RequestHandler handler) {
        this.routeGroup.any(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder any(String path, ContextHandler handler) {
        this.routeGroup.any(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder anyOf(Set<HttpMethod> methods, String path, RequestHandler handler) {
        this.routeGroup.anyOf(methods, path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder anyOf(Set<HttpMethod> methods, String path, ContextHandler handler) {
        this.routeGroup.anyOf(methods, path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder delete(String path, RequestHandler handler) {
        this.routeGroup.delete(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder delete(String path, ContextHandler handler) {
        this.routeGroup.delete(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder get(String path, RequestHandler handler) {
        this.routeGroup.get(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder get(String path, ContextHandler handler) {
        this.routeGroup.get(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder head(String path, RequestHandler handler) {
        this.routeGroup.head(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder head(String path, ContextHandler handler) {
        this.routeGroup.head(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder options(String path, RequestHandler handler) {
        this.routeGroup.options(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder options(String path, ContextHandler handler) {
        this.routeGroup.options(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder patch(String path, RequestHandler handler) {
        this.routeGroup.patch(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder patch(String path, ContextHandler handler) {
        this.routeGroup.patch(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder post(String path, RequestHandler handler) {
        this.routeGroup.post(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder post(String path, ContextHandler handler) {
        this.routeGroup.post(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder put(String path, RequestHandler handler) {
        this.routeGroup.put(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder put(String path, ContextHandler handler) {
        this.routeGroup.put(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder trace(String path, RequestHandler handler) {
        this.routeGroup.trace(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder trace(String path, ContextHandler handler) {
        this.routeGroup.trace(path, handler);
        return this;
    }

    @Override
    public RouteGroupBuilder group(String path, RouteGroupHandler handler) {
        this.routeGroup.group(path, handler);
        return this;
    }

    @Override
    public RouteGroup build() {
        return this.routeGroup;
    }
}
