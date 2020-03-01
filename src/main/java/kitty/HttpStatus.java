package kitty;

import java.util.stream.Stream;

public enum HttpStatus {
    OK(200, "OK"),
    CREATED(201, "Created"),
    METHOD_NOW_ALLOWED(405, "Method Not Allowed"),
    NOT_FOUND(404, "Not Found");

    private final int statusCode;
    private final String reasonPhrase;

    HttpStatus(int statusCode, String reasonPhrase) {
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }

    public static HttpStatus of(int statusCode) {
        return Stream.of(values())
                .filter(httpStatus -> httpStatus.statusCode == statusCode)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown HTTP Status"));
    }

    public int statusCode() {
        return statusCode;
    }

    public String reasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public String toString() {
        return statusCode + " " + reasonPhrase;
    }
}
