package com.julianjupiter.kitty.http.message;

import com.julianjupiter.kitty.http.message.util.HttpStatus;

/**
 * @author Julian Jupiter
 */
public interface Response extends Mesgit sage {
    HttpStatus status();

    Response status(int statusCode);

    Response status(HttpStatus status);

    Response header(String name, String value);

    Response header(Header header);

    Response headers(Header[] headers);

    Response cookie(String name, String value);

    Response cookie(Cookie cookie);

    Response cookies(Cookie[] cookies);

    Response body(Object body);
}
