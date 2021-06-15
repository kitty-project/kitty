package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpMethod;
import com.julianjupiter.kitty.http.server.ContextHandler;
import com.julianjupiter.kitty.http.server.RequestHandler;
import com.julianjupiter.kitty.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Julian Jupiter
 */
final class KittyRouter implements Router {
    private final Map<String, List<Route>> routeMap = new HashMap<>();

    @Override
    public Map<String, List<Route>> routes() {
        return Map.copyOf(this.routeMap);
    }

    @Override
    public Router route(HttpMethod method, String path, RequestHandler handler) {
        this.createRoute(method, path, handler);
        return this;
    }

    @Override
    public Router route(HttpMethod method, String path, ContextHandler handler) {
        this.createRoute(method, path, handler);
        return this;
    }

    @Override
    public Router any(String path, RequestHandler handler) {
        var routes = Arrays.stream(HttpMethod.values())
                .map(method -> this.createRoute(method, path, handler))
                .toList();
        this.addRoutes(routes);
        return this;
    }

    @Override
    public Router any(String path, ContextHandler handler) {
        var routes = Arrays.stream(HttpMethod.values())
                .map(method -> this.createRoute(method, path, handler))
                .toList();
        this.addRoutes(routes);
        return this;
    }

    @Override
    public Router anyOf(Set<HttpMethod> methods, String path, RequestHandler handler) {
        var routes = methods.stream()
                .map(method -> this.createRoute(method, path, handler))
                .toList();
        this.addRoutes(routes);
        return this;
    }

    @Override
    public Router anyOf(Set<HttpMethod> methods, String path, ContextHandler handler) {
        var routes = methods.stream()
                .map(method -> this.createRoute(method, path, handler))
                .toList();
        this.addRoutes(routes);
        return this;
    }

    @Override
    public Router delete(String path, RequestHandler handler) {
        var route = this.createRoute(HttpMethod.DELETE, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router delete(String path, ContextHandler handler) {
        var route = this.createRoute(HttpMethod.DELETE, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router get(String path, RequestHandler handler) {
        var route = this.createRoute(HttpMethod.GET, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router get(String path, ContextHandler handler) {
        var route = this.createRoute(HttpMethod.GET, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router head(String path, RequestHandler handler) {
        var route = this.createRoute(HttpMethod.HEAD, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router head(String path, ContextHandler handler) {
        var route = this.createRoute(HttpMethod.HEAD, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router options(String path, RequestHandler handler) {
        var route = this.createRoute(HttpMethod.OPTIONS, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router options(String path, ContextHandler handler) {
        var route = this.createRoute(HttpMethod.OPTIONS, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router patch(String path, RequestHandler handler) {
        var route = this.createRoute(HttpMethod.PATCH, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router patch(String path, ContextHandler handler) {
        var route = this.createRoute(HttpMethod.PATCH, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router post(String path, RequestHandler handler) {
        var route = this.createRoute(HttpMethod.POST, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router post(String path, ContextHandler handler) {
        var route = this.createRoute(HttpMethod.POST, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router put(String path, RequestHandler handler) {
        var route = this.createRoute(HttpMethod.PUT, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router put(String path, ContextHandler handler) {
        var route = this.createRoute(HttpMethod.PUT, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router trace(String path, RequestHandler handler) {
        var route = this.createRoute(HttpMethod.TRACE, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router trace(String path, ContextHandler handler) {
        var route = this.createRoute(HttpMethod.TRACE, path, handler);
        this.addRoute(route);
        return this;
    }

    @Override
    public Router group(String path, RouteGroupHandler handler) {
        var routeGroup = handler.handle(RouteGroupBuilder.create());
        routeGroup.routes().stream()
                .map(route -> {
                    var resolvedPath = this.resolvePath(path);
                    var resolvedRoutePath = this.resolvePath(route.path());
                    var routePath = resolvedPath + Constants.Characters.FORWARD_SLASH + resolvedRoutePath;
                    return new KittyRouteGroup.RouteGroupRoute(route.method(), Constants.Characters.FORWARD_SLASH + this.resolvePath(routePath), route.handler());
                })
                .forEach(route -> {
                    var routeHandler = route.handler();
                    if (routeHandler instanceof RequestHandler requestHandler) {
                        this.addRoute(this.createRoute(route.method(), route.path(), requestHandler));
                    } else {
                        this.addRoute(this.createRoute(route.method(), route.path(), (ContextHandler) routeHandler));
                    }
                });

        return this;
    }

    private Route createRoute(HttpMethod method, String path, RequestHandler handler) {
        Handler kittyHandler = handler::handle;
        return new KittyRoute(method, path, kittyHandler);
    }

    private Route createRoute(HttpMethod method, String path, ContextHandler handler) {
        Handler kittyHandler = (request, response) -> handler.handle(new KittyContext(request, response));
        return new KittyRoute(method, path, kittyHandler);
    }

    private void addRoute(Route route) {
        var compiledPath = PathPatternCompiler.compile(route.path());
        this.routeMap.compute(compiledPath, (key, value) -> {
            if (value == null) {
                value = new ArrayList<>();
            }
//            var finalRoute = new KittyRoute(route.method(), compiledPath, route.handler());
            value.add(route);
            return value;
        });
    }

    private void addRoutes(List<Route> routes) {
        routes.forEach(this::addRoute);
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
