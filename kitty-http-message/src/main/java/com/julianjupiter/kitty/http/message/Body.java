package com.julianjupiter.kitty.http.message;

/**
 * @author Julian Jupiter
 */
public interface Body {
    boolean isReadable();

    boolean isWritable();

    int size();
}
