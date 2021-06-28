package com.julianjupiter.kitty.http.message;

/**
 * @author Julian Jupiter
 */
public interface PathParam {
    String name();

    String asString();

    int asInt();

    long asLong();
}
