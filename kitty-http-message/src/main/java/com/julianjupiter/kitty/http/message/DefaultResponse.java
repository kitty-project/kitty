package com.julianjupiter.kitty.http.message;

/**
 * @author Julian Jupiter
 */
final class DefaultResponse extends DefaultMessage implements Response {
    @Override
    public HttpStatus status() {
        return null;
    }

    @Override
    public Response status(int statusCode) {
        return null;
    }

    @Override
    public Response status(HttpStatus status) {
        return null;
    }

    @Override
    public Response header(String header) {
        return null;
    }

    @Override
    public Response header(HttpHeader header) {
        return null;
    }

    @Override
    public Response headers(HttpHeaders httpHeaders) {
        return null;
    }

}
