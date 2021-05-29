package com.julianjupiter.kitty;

/**
 * @author Julian Jupiter
 */
public record PathPattern(String value) {
    @Override
    public String toString() {
        return value;
    }
}
