package com.julianjupiter.kitty;

public class PathParam {
    private final Object value;

    private PathParam(Object value) {
        this.value = value;
    }

    public static PathParam of(Object param) {
        return new PathParam(null);
    }

    public Integer toInt() {
        if (value == null) {
            return null;
        }

        return Integer.valueOf((String) value);
    }

    public Long toLong() {
        if (value == null) {
            return null;
        }

        return Long.valueOf((String) value);
    }

    @Override
    public String toString() {
        return (String) value;
    }
}
