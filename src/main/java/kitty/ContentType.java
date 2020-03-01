package kitty;

import java.util.Objects;
import java.util.stream.Stream;

public enum ContentType {
    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    TEXT_HTML("text/html"),
    TEXT_PLAIN("text/plain"),
    UNKNOWN("unknown");

    private final String value;

    ContentType(String value) {
        this.value = value;
    }

    public static ContentType of(String contentType) {
        Objects.requireNonNull(contentType, "Parameter 'contentType' is null!");

        return Stream.of(values())
                .filter(ct -> ct.value.equals(contentType))
                .findFirst()
                .orElse(ContentType.UNKNOWN);
    }

    @Override
    public String toString() {
        return value;
    }
}
