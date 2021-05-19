package com.julianjupiter.kitty;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class RouterImpl implements Router {
    private final String contextPath;
    private boolean withContextPath = true;
    private Map<Integer, Pair<Boolean, Set<Route>>> routeMap;
    private Set<Route> routes;
    private int counter;

    public RouterImpl(String contextPath) {
        this.contextPath = contextPath;
        this.routeMap = new HashMap<>();
        this.routes = new HashSet<>();
    }

    @Override
    public Set<Pair<PathMatcher, Route>> routes() {
        this.updatePreviousRoutes();

        return routeMap.values().stream()
                .map(pair -> {
                    if (pair.first()) {
                        return pair.second().stream()
                                .map(route -> new RouteImpl(route.method(), this.path(route.path()), route.handler()))
                                .collect(Collectors.toSet());
                    }

                    return pair.second();
                })
                .flatMap(Set::stream)
                .map(route -> Pair.<PathMatcher, Route>of(PathPattern.compile(route.path()), route))
                .collect(Collectors.toSet());
    }

    @Override
    public Router any(String path, RequestHandler handler) {
        this.updatePreviousRoutes();
        counter++;
        Set<Route> routes = Stream.of(HttpMethod.values())
                .map(method -> new RouteImpl(method, path, handler))
                .collect(Collectors.toUnmodifiableSet());
        Pair<Boolean, Set<Route>> pair = Pair.of(true, routes);
        this.routeMap.put(counter, pair);
        return this;
    }

    @Override
    public Router anyOf(Set<HttpMethod> methods, String path, RequestHandler handler) {
        if (methods.size() < 1) {
            throw new IllegalArgumentException("Requires at least one HttpMethod for path \"" + path + "\"");
        }

        this.updatePreviousRoutes();
        counter++;
        Set<Route> routes = methods.stream()
                .map(method -> new RouteImpl(method, path, handler))
                .collect(Collectors.toUnmodifiableSet());
        Pair<Boolean, Set<Route>> pair = Pair.of(true, routes);
        this.routeMap.put(counter, pair);
        return this;
    }

    @Override
    public Router delete(String path, RequestHandler handler) {
        this.updatePreviousRoutes();
        counter++;
        Set<Route> routes = Set.of(new RouteImpl(HttpMethod.DELETE, path, handler));
        Pair<Boolean, Set<Route>> pair = Pair.of(true, routes);
        this.routeMap.put(counter, pair);
        return this;
    }

    @Override
    public Router get(String path, RequestHandler handler) {
        this.updatePreviousRoutes();
        counter++;
        Set<Route> routes = Set.of(new RouteImpl(HttpMethod.GET, path, handler));
        Pair<Boolean, Set<Route>> pair = Pair.of(true, routes);
        this.routeMap.put(counter, pair);
        return this;
    }

    @Override
    public Router head(String path, RequestHandler handler) {
        this.updatePreviousRoutes();
        counter++;
        Set<Route> routes = Set.of(new RouteImpl(HttpMethod.HEAD, path, handler));
        Pair<Boolean, Set<Route>> pair = Pair.of(true, routes);
        this.routeMap.put(counter, pair);
        return this;
    }

    @Override
    public Router options(String path, RequestHandler handler) {
        this.updatePreviousRoutes();
        counter++;
        Set<Route> routes = Set.of(new RouteImpl(HttpMethod.OPTIONS, path, handler));
        Pair<Boolean, Set<Route>> pair = Pair.of(true, routes);
        this.routeMap.put(counter, pair);
        return this;
    }

    @Override
    public Router patch(String path, RequestHandler handler) {
        this.updatePreviousRoutes();
        counter++;
        Set<Route> routes = Set.of(new RouteImpl(HttpMethod.PATCH, path, handler));
        Pair<Boolean, Set<Route>> pair = Pair.of(true, routes);
        this.routeMap.put(counter, pair);
        return this;
    }

    @Override
    public Router post(String path, RequestHandler handler) {
        this.updatePreviousRoutes();
        counter++;
        Set<Route> routes = Set.of(new RouteImpl(HttpMethod.POST, path, handler));
        Pair<Boolean, Set<Route>> pair = Pair.of(true, routes);
        this.routeMap.put(counter, pair);
        return this;
    }

    @Override
    public Router put(String path, RequestHandler handler) {
        this.updatePreviousRoutes();
        counter++;
        Set<Route> routes = Set.of(new RouteImpl(HttpMethod.PUT, path, handler));
        Pair<Boolean, Set<Route>> pair = Pair.of(true, routes);
        this.routeMap.put(counter, pair);
        return this;
    }

    @Override
    public Router trace(String path, RequestHandler handler) {
        this.updatePreviousRoutes();
        counter++;
        Set<Route> routes = Set.of(new RouteImpl(HttpMethod.TRACE, path, handler));
        Pair<Boolean, Set<Route>> pair = Pair.of(true, routes);
        this.routeMap.put(counter, pair);
        return this;
    }

    @Override
    public Router withoutContextPath() {
        this.withContextPath = false;
        return this;
    }

    private void addRoute(HttpMethod method, String path, RequestHandler handler) {
        this.routes.add(new RouteImpl(method, this.path(path), handler));
    }

    private void updatePreviousRoutes() {
        if (counter > 0 && !withContextPath) {
            Pair<Boolean, Set<Route>> pair = this.routeMap.get(counter);
            Pair<Boolean, Set<Route>> newPair = Pair.of(false, pair.second());
            this.routeMap.put(counter, newPair);
            this.withContextPath = true;
        }
    }

    private String path(String path) {
        String pathWithLeadingForwardSlash = Optional.of(path)
                .filter(p -> p.startsWith("/"))
                .orElse("/" + path);
        String finalPath = Optional.of(pathWithLeadingForwardSlash)
                .filter(p -> p.length() == 1 || !p.endsWith("/"))
                .orElse(pathWithLeadingForwardSlash.substring(0, pathWithLeadingForwardSlash.length() - 1));

        if (withContextPath) {
            Optional<String> contextPathOptional = Optional.of(contextPath)
                    .filter(cp -> cp.equals("/"));

            if (contextPathOptional.isPresent()) {
                return finalPath;
            }

            return contextPath + finalPath;
        }

        return finalPath;
    }
}
