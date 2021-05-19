package com.julianjupiter.kitty;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathPattern {
    public static PathMatcher compile(String pattern) {
        Objects.requireNonNull(pattern, "Parameter 'pattern' is null!");

        var compiledPathPattern = Stream.of(pattern.split("/"))
                .map(segment -> {
                    if (segment.startsWith("{") && segment.endsWith("}")) {
                        if (segment.length() == 2) {
                            return "[^/]+";
                        } else {
                            var index = indexOf(segment, "[{]{1}[a-zA-Z0-9]*[:]{1}");

                            if (index != -1) {
                                return segment.substring(index, segment.length() - 1);
                            } else {
                                return "[^/]+";
                            }
                        }
                    } else {
                        return segment;
                    }
                })
                .collect(Collectors.joining("/"));

        return new PathMatcherImpl(compiledPathPattern);
    }

    private static int indexOf(String string, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(string);

        if (matcher.find()) {
            return matcher.end();
        }

        return -1;
    }
}
