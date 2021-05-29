package com.julianjupiter.kitty;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Julian Jupiter
 */
class PathPatternCompiler {
    private PathPatternCompiler() {
    }

    public static String compile(String path) {
        Objects.requireNonNull(path, "path cannot be null!");

        return Arrays.stream(path.split("/"))
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
    }

    private static int indexOf(String string) {
        var matcher = Pattern.compile("[{]{1}[a-zA-Z0-9]*[:]{1}").matcher(string);

        if (matcher.find()) {
            return matcher.end();
        }

        return -1;
    }
}
