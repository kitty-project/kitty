package com.julianjupiter.kitty;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author Julian Jupiter
 */
class KittyPathMatcher implements PathMatcher {
    private final PathPattern pattern;

    public KittyPathMatcher(PathPattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public MatchResult match(String path) {
        Objects.requireNonNull(path, "path cannot be null");

        boolean matched = Pattern.matches(pattern.value(), path);

        if (matched) {
            return PositiveResult.create(null);
        }

        return NegativeResult.create();
    }
}