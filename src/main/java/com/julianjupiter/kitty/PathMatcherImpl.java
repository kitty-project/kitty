package com.julianjupiter.kitty;

import java.util.Objects;
import java.util.regex.Pattern;

public class PathMatcherImpl implements PathMatcher {
    private final String pathPattern;

    public PathMatcherImpl(String pathPattern) {
        this.pathPattern = pathPattern;
    }

    @Override
    public MatchResult match(String path) {
        Objects.requireNonNull(path, "Parameter 'path' is null!");

        boolean matched = Pattern.matches(pathPattern, path);

        if (matched) {
            return PositiveResult.create(null);
        }

        return NegativeResult.create();
    }
}
