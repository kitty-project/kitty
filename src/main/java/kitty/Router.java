package kitty;

import java.util.Set;

public interface Router {
    Set<Route> routes();
    Router any(String path, RequestHandler handler);
    Router anyOf(Set<HttpMethod> methods, String path, RequestHandler handler);
    Router delete(String path, RequestHandler handler);
    Router get(String path, RequestHandler handler);
    Router head(String path, RequestHandler handler);
    Router options(String path, RequestHandler handler);
    Router patch(String path, RequestHandler handler);
    Router post(String path, RequestHandler handler);
    Router put(String path, RequestHandler handler);
    Router trace(String path, RequestHandler handler);
    Router withoutContextPath();
}
