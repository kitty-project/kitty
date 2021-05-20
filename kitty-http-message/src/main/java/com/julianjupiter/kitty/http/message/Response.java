package com.julianjupiter.kitty.http.message;

/**
 * @author Julian Jupiter
 */
public interface Response extends Message {

    HttpStatus status();

    Response status(int statusCode);

    Response status(HttpStatus status);

    StatusLine statusLine();

    Response header(String name, String value);

    Response header(HttpHeader header);

    Response headers(HttpHeaders httpHeaders);

    Response body(HttpBody body);

}
