package kitty;

public interface Response {
    Response body(Object object);

    Response status(int code);

    Response status(HttpStatus status);
}
