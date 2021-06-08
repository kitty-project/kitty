package com.julianjupiter.kitty;

/**
 * @author Julian Jupiter
 */
sealed interface PathMatcher permits KittyPathMatcher {
    static PathMatcher create(String pattern) {
        return new KittyPathMatcher(pattern);
    }

    String pattern();

    MatchResult match(String path);
}