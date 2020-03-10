package com.julianjupiter.kitty;

public class PathParam {
    private final Object value;

    private PathParam(Object value) {
        this.value = value;
    }

    public static PathParam of(Object param) {
        return new PathParam(null);
    }

    public Integer asInt() {
        return Integer.valueOf((String) value);
    }

    public String asString() {
        return (String) value;
    }
}
