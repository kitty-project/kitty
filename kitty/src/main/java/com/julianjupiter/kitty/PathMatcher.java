package com.julianjupiter.kitty;

/**
 * @author Julian Jupiter
 */
public interface PathMatcher {
    static PathMatcher create(PathPattern pattern) {
        return PathPatternCompiler.compile(pattern);
    }

    MatchResult match(String path);
}