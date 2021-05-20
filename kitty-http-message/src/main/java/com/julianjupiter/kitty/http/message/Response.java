package com.julianjupiter.kitty.http.message;

/**
 * @author Julian Jupiter
 */
public interface Response extends Message {

    HttpStatus status();

    Response status(int statusCode);

    Response status(HttpStatus status);

    Response header(String header);

    Response header(HttpHeader header);

    Response headers(HttpHeaders httpHeaders);
}
