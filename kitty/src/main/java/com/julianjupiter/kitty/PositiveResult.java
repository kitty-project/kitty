package com.julianjupiter.kitty;

import java.util.Map;
import java.util.Objects;

record PositiveResult(Map<String, String> params) implements MatchResult {

    public static PositiveResult create(Map<String, String> params) {
        return new PositiveResult(Objects.requireNonNullElse(params, Map.of()));
    }

    @Override
    public boolean matches() {
        return true;
    }

    @Override
    public Map<String, String> params() {
        return params;
    }

    @Override
    public String param(String name) {
        return params.get(name);
    }
}
