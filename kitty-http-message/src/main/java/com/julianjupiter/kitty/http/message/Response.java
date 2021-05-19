package com.julianjupiter.kitty.http.message;

/**
 * @author Julian Jupiter
 */
public interface Response extends Message {
    ProtocolVersion version();

    HttpStatus status();

    Response status(int statusCode);

    Response status(HttpStatus status);

    HttpHeaders headers();

    Response header(String header);

    Response header(HttpHeader header);

    Response headers(HttpHeaders httpHeaders);

    HttpBody body();

    Response ok();
}
