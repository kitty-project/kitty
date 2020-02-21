package kitty;

import java.util.Set;
import java.util.stream.Stream;

class RouterImpl implements Router {
    private Set<Route> routes;

    public RouterImpl(Set<Route> routes) {
        this.routes = routes;
    }

    @Override
    public Set<Route> routes() {
        return routes;
    }

    @Override
    public Router any(String path, RequestHandler handler) {
        Stream.of(HttpMethod.values())
                .forEach(method -> this.addRoute(method, path, handler));
        return this;
    }

    @Override
    public Router anyOf(Set<HttpMethod> methods, String path, RequestHandler handler) {
        if (methods.size() < 1) {
            throw new IllegalArgumentException("Requires at least one HttpMethod for path \"" + path + "\"");
        }

        methods.forEach(method -> this.addRoute(method, path, handler));
        return this;
    }

    @Override
    public Router delete(String path, RequestHandler handler) {
        this.addRoute(HttpMethod.DELETE, path, handler);
        return this;
    }

    @Override
    public Router get(String path, RequestHandler handler) {
        this.addRoute(HttpMethod.GET, path, handler);
        return this;
    }

    @Override
    public Router head(String path, RequestHandler handler) {
        this.addRoute(HttpMethod.HEAD, path, handler);
        return this;
    }

    @Override
    public Router options(String path, RequestHandler handler) {
        this.addRoute(HttpMethod.OPTIONS, path, handler);
        return this;
    }

    @Override
    public Router patch(String path, RequestHandler handler) {
        this.addRoute(HttpMethod.PATCH, path, handler);
        return this;
    }

    @Override
    public Router post(String path, RequestHandler handler) {
        this.addRoute(HttpMethod.POST, path, handler);
        return this;
    }

    @Override
    public Router put(String path, RequestHandler handler) {
        this.addRoute(HttpMethod.PUT, path, handler);
        return this;
    }

    @Override
    public Router trace(String path, RequestHandler handler) {
        this.addRoute(HttpMethod.TRACE, path, handler);
        return this;
    }

    private void addRoute(HttpMethod method, String path, RequestHandler handler) {
        this.routes.add(new RouteImpl(method, path, handler));
    }
}
