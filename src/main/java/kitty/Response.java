package kitty;

public interface Response {

    ContentType contentType();

    Response contentType(ContentType contentType);

    Response html();

    Response json();

    Response xml();

    Object body();

    Response body(Object object);

    HttpStatus status();

    Response status(int code);

    Response status(HttpStatus status);

    Response render(String template);
}
