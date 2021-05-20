package com.julianjupiter.kitty.http.message;

/**
 * @author Julian Jupiter
 */
public interface StatusLine {

    HttpVersion version();

    HttpStatus status();

}
