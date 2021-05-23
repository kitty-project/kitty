package com.julianjupiter.kitty;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Julian Jupiter
 */
class PathPatternCompiler {
    private PathPatternCompiler() {
    }

    public static PathMatcher compile(PathPattern pattern) {
        Objects.requireNonNull(pattern, "pattern cannot be null!");

        var compiledPathPattern = Stream.of(pattern.value().split("/"))
                .map(segment -> {
                    if (segment.startsWith("{") && segment.endsWith("}")) {
                        if (segment.length() == 2) {
                            return "[^/]+";
                        } else {
                            var index = indexOf(segment);

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

        return new KittyPathMatcher(new PathPattern(compiledPathPattern));
    }

    private static int indexOf(String string) {
        var matcher = Pattern.compile("[{]{1}[a-zA-Z0-9]*[:]{1}").matcher(string);

        if (matcher.find()) {
            return matcher.end();
        }

        return -1;
    }
}
