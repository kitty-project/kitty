package kitty;

public interface RequestHandler {
    Response handle(Request request, Response response);
}
