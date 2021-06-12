package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpMethod;
import com.julianjupiter.kitty.http.server.ContextHandler;
import com.julianjupiter.kitty.http.server.RequestHandler;
import com.julianjupiter.kitty.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Julian Jupiter
 */
final class KittyRouteGroup implements RouteGroup {
    private final List<RouteGroupRoute> routes = new ArrayList<>();

    @Override
    public List<RouteGroupRoute> routes() {
        return this.routes;
    }

    @Override
    public RouteGroup route(HttpMethod method, String path, RequestHandler handler) {
        this.routes.add(new RouteGroupRoute(method, path, handler));
        return this;
    }

    @Override
    public RouteGroup route(HttpMethod method, String path, ContextHandler handler) {
        this.routes.add(new RouteGroupRoute(method, path, handler));
        return this;
    }

    @Override
    public RouteGroup any(String path, RequestHandler handler) {
        Arrays.stream(HttpMethod.values())
                .forEach(method -> this.routes.add(new RouteGroupRoute(method, path, handler)));
        return this;
    }

    @Override
    public RouteGroup any(String path, ContextHandler handler) {
        Arrays.stream(HttpMethod.values())
                .forEach(method -> this.routes.add(new RouteGroupRoute(method, path, handler)));
        return this;
    }

    @Override
    public RouteGroup anyOf(Set<HttpMethod> methods, String path, RequestHandler handler) {
        methods.forEach(method -> this.routes.add(new RouteGroupRoute(method, path, handler)));
        return this;
    }

    @Override
    public RouteGroup anyOf(Set<HttpMethod> methods, String path, ContextHandler handler) {
        methods.forEach(method -> this.routes.add(new RouteGroupRoute(method, path, handler)));
        return this;
    }

    @Override
    public RouteGroup delete(String path, RequestHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.DELETE, path, handler));
        return this;
    }

    @Override
    public RouteGroup delete(String path, ContextHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.DELETE, path, handler));
        return this;
    }

    @Override
    public RouteGroup get(String path, RequestHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.GET, path, handler));
        return this;
    }

    @Override
    public RouteGroup get(String path, ContextHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.GET, path, handler));
        return this;
    }

    @Override
    public RouteGroup head(String path, RequestHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.HEAD, path, handler));
        return this;
    }

    @Override
    public RouteGroup head(String path, ContextHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.HEAD, path, handler));
        return this;
    }

    @Override
    public RouteGroup options(String path, RequestHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.OPTIONS, path, handler));
        return this;
    }

    @Override
    public RouteGroup options(String path, ContextHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.OPTIONS, path, handler));
        return this;
    }

    @Override
    public RouteGroup patch(String path, RequestHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.PATCH, path, handler));
        return this;
    }

    @Override
    public RouteGroup patch(String path, ContextHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.PATCH, path, handler));
        return this;
    }

    @Override
    public RouteGroup post(String path, RequestHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.POST, path, handler));
        return this;
    }

    @Override
    public RouteGroup post(String path, ContextHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.POST, path, handler));
        return this;
    }

    @Override
    public RouteGroup put(String path, RequestHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.PUT, path, handler));
        return this;
    }

    @Override
    public RouteGroup put(String path, ContextHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.PUT, path, handler));
        return this;
    }

    @Override
    public RouteGroup trace(String path, RequestHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.TRACE, path, handler));
        return this;
    }

    @Override
    public RouteGroup trace(String path, ContextHandler handler) {
        this.routes.add(new RouteGroupRoute(HttpMethod.TRACE, path, handler));
        return this;
    }

    @Override
    public RouteGroup group(String path, RouteGroupHandler handler) {
        var routeGroup = handler.handle(RouteGroupBuilder.create());
        routeGroup.routes()
                .forEach(route -> {
                    var resolvedPath = this.resolvePath(path);
                    var resolvedRoutePath = this.resolvePath(route.path());
                    var routePath = resolvedPath + Constants.Characters.FORWARD_SLASH + resolvedRoutePath;
                    this.routes.add(new RouteGroupRoute(route.method(), Constants.Characters.FORWARD_SLASH + this.resolvePath(routePath), route.handler()));
                });
        return this;
    }

    record RouteGroupRoute(HttpMethod method, String path, Object handler) {
    }

    private String resolvePath(String path) {
        if (path == null || path.isBlank()) {
            return Constants.Characters.EMPTY_SPACE;
        }

        if (path.endsWith(Constants.Characters.FORWARD_SLASH)) {
            return this.resolvePath(path.substring(0, path.length() - 1));
        }

        if (path.startsWith(Constants.Characters.FORWARD_SLASH)) {
            return this.resolvePath(path.substring(1));
        }

        return path;
    }
}
