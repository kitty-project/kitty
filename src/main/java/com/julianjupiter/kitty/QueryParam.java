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
        if (values[0] == null) {
            return null;
        }

        return Integer.valueOf((String) values[0]);
    }

    public Long toLong() {
        if (values[0] == null) {
            return null;
        }

        return Long.valueOf(values[0]);
    }

    @Override
    public String toString() {
        return values[0];
    }
}
