package com.julianjupiter.kitty.http;

/**
 * @author Julian Jupiter
 */
public interface Body {
    boolean isReadable();

    boolean isWritable();

    int size();
}
