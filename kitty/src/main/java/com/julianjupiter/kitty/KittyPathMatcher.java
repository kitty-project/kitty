package com.julianjupiter.kitty;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author Julian Jupiter
 */
final record KittyPathMatcher(String pattern) implements PathMatcher {
    @Override
    public MatchResult match(String requestPath) {
        Objects.requireNonNull(requestPath, "requestPath cannot be null");

        boolean matched = Pattern.matches(this.pattern, requestPath);

        if (matched) {
            return PositiveResult.create(null);
        }

        return NegativeResult.create();
    }
}