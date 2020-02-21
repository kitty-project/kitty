package kitty;

public interface Route {
    HttpMethod method();

    String path();

    RequestHandler handler();
}
