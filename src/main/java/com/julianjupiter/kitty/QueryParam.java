package com.julianjupiter.kitty;

public class QueryParam {
    private final Object value;

    private QueryParam(Object value) {
        this.value = value;
    }

    public static QueryParam of(Object param) {
        return new QueryParam(null);
    }

    public Integer asInt() {
        return Integer.valueOf((String) value);
    }

    public String asString() {
        return (String) value;
    }
}
