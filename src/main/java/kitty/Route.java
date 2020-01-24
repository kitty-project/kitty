package kitty;

public interface Route {
    String method();

    String path();

    RequestHandler handler();
}
