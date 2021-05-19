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
        if (values != null && values[0] != null && !values[0].isBlank()) {
            return Integer.valueOf((String) values[0]);
        }

        return null;
    }

    public Long toLong() {
        if (values != null && values[0] != null && !values[0].isBlank()) {
            return Long.valueOf(values[0]);
        }

        return null;
    }

    public Boolean toBoolean() {
        if (values != null && values[0] != null && !values[0].isBlank()) {
            return Boolean.valueOf(values[0]);
        }

        return null;
    }

    @Override
    public String toString() {
        return values[0];
    }
}
