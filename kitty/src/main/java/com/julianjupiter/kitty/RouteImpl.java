package com.julianjupiter.kitty;

class RouteImpl implements Route {
    private final HttpMethod method;
    private final String path;
    private final RequestHandler handler;

    RouteImpl(HttpMethod method, String path, RequestHandler handler) {
        this.method = method;
        this.path = path;
        this.handler = handler;
    }

    @Override
    public HttpMethod method() {
        return method;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public RequestHandler handler() {
        return handler;
    }
}
