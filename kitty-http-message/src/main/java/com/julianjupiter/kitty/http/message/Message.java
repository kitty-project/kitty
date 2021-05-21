package com.julianjupiter.kitty.http.message;

/**
 * @author Julian Jupiter
 */
public interface Message {
    HttpVersion version();

    HttpHeaders headers();

    Body body();
}
