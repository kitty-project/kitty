package kitty;

class RouteImpl implements Route {
    private final String method;
    private final String path;
    private final RequestHandler handler;

    public RouteImpl(String method, String path, RequestHandler handler) {
        this.method = method;
        this.path = path;
        this.handler = handler;
    }

    @Override
    public String method() {
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
