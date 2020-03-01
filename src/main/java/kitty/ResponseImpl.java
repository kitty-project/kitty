package kitty;

import java.io.Writer;
import java.util.Map;
import java.util.Objects;

class ResponseImpl implements Response {
    private ContentType contentType = ContentType.TEXT_PLAIN;
    private Object body;
    private HttpStatus httpStatus = HttpStatus.OK;
    private ViewResolver viewResolver = new PebbleViewResolver();

    @Override
    public ContentType contentType() {
        return contentType;
    }

    @Override
    public Response contentType(ContentType contentType) {
        this.contentType = contentType;
        return this;
    }

    @Override
    public Response html() {
        this.contentType = ContentType.TEXT_HTML;
        return this;
    }

    @Override
    public Response json() {
        this.contentType = ContentType.APPLICATION_JSON;
        return this;
    }

    @Override
    public Response xml() {
        this.contentType = ContentType.APPLICATION_XML;
        return this;
    }

    @Override
    public Object body() {
        return body;
    }

    @Override
    public Response body(Object body) {
        this.body = body;
        return this;
    }

    @Override
    public HttpStatus status() {
        return httpStatus;
    }

    @Override
    public Response status(int statusCode) {
        this.httpStatus = HttpStatus.of(statusCode);
        return this;
    }

    @Override
    public Response status(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    @Override
    public Response render(String template) {
        Objects.requireNonNull(template, "Parameter 'template' is null!");

        if (this.viewResolver != null) {
            Writer writer;
            if (this.body != null && this.body instanceof Map<?, ?>) {
                Map<String, Object> attributes = (Map<String, Object>) this.body;
                writer = this.viewResolver.process(template, attributes);
            } else {
                writer = this.viewResolver.process(template, null);
            }

            this.body = writer.toString();
        } else {
            this.body = "<!doctype html><html><head><meta charset=\"UTF-8\"><title>Kitty Error</title></head><body><p>No ViewResolver has been configured.</p></body></html>";
        }

        this.contentType = ContentType.TEXT_HTML;

        return this;
    }

}
