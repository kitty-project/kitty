package kitty;

import java.util.Set;

class RouterImpl implements Router {
    private Set<Route> routes;

    @Override
    public Router any(RequestHandler handler) {
        return null;
    }

    @Override
    public Router anyOf(Set<String> methods, RequestHandler handler) {
        return null;
    }

    @Override
    public Router delete(String path, RequestHandler handler) {
        return null;
    }

    @Override
    public Router get(String path, RequestHandler handler) {
        return null;
    }

    @Override
    public Router head(String path, RequestHandler handler) {
        return null;
    }

    @Override
    public Router options(String path, RequestHandler handler) {
        return null;
    }

    @Override
    public Router patch(String path, RequestHandler handler) {
        return null;
    }

    @Override
    public Router post(String path, RequestHandler handler) {
        return null;
    }

    @Override
    public Router put(String path, RequestHandler handler) {
        return null;
    }

    @Override
    public Router trace(String path, RequestHandler handler) {
        return null;
    }
}
