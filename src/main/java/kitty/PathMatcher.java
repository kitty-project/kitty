package kitty;

public interface PathMatcher {
    static PathMatcher create(String pathPattern) {
        return PathPattern.compile(pathPattern);
    }

    MatchResult match(String path);

}
