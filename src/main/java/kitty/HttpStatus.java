package kitty;

public enum HttpStatus {
    OK(200, "OK"),
    CREATED(201, "Created"),
    NOT_FOUND(404, "Not Found");

    private final int code;
    private final String reasonPhrase;

    HttpStatus(int code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    public int code() {
        return code;
    }

    public String reasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public String toString() {
        return code + " " + reasonPhrase;
    }
}
