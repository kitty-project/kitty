package com.julianjupiter.kitty;

public class QueryParam {
    private final String[] values;

    private QueryParam(String[] values) {
        this.values = values;
    }

    public static QueryParam of(String[] values) {
        return new QueryParam(values);
    }

    public Integer toInt() {
        return Integer.valueOf((String) values[0]);
    }

    public Long toLong() {
        return Long.valueOf(values[0]);
    }

    @Override
    public String toString() {
        return values[0];
    }
}
