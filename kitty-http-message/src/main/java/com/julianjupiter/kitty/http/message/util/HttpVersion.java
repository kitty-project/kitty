package com.julianjupiter.kitty.http.message.util;

import java.util.Arrays;

/**
 * @author Julian Jupiter
 */
public enum HttpVersion {
    HTTP_1_0(1, 0),
    HTTP_1_1(1, 1),
    HTTP_2(2, -1),
    HTTP_3(3, -1);

    private static final String INVALID_PROTOCOL_VERSION = "Invalid HTTP protocol version";
    private static final String PROTOCOL = "HTTP";
    private final int majorVersion;
    private final int minorVersion;

    HttpVersion(int majorVersion, int minorVersion) {
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
    }

    /**
     * Static method that accepts HTTP version string.
     *
     * @param httpVersion HTTP version whose format is either HTTP/x, HTTP/x.x, x, or x.x.
     * @return HttpVersion
     */
    public static HttpVersion fromString(String httpVersion) {

        if (httpVersion == null || httpVersion.isBlank()) {
            throw new IllegalArgumentException("HTTP protocol version must not be null or empty");
        }

        var s = httpVersion.split("/");

        if (s.length == 1) {
            var s1 = s[0].split("\\.");
            if (s1.length == 1) {
                return of(parseMajorVersion(s1[0]));
            }

            if (s1.length == 2) {
                var s2 = parseMajorAndMinorVersions(s1[0], s1[1]);
                return of(s2[0], s2[1]);
            }
        }

        if (s.length == 2) {
            var s1 = s[1].split("\\.");
            if (s1.length == 1) {
                return of(parseMajorVersion(s1[0]));
            }

            if (s1.length == 2) {
                var s2 = parseMajorAndMinorVersions(s1[0], s1[1]);
                return of(s2[0], s2[1]);
            }
        }

        throw new IllegalArgumentException(INVALID_PROTOCOL_VERSION);
    }

    private static int parseMajorVersion(String arg) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(INVALID_PROTOCOL_VERSION + ": " + arg);
        }
    }

    private static int[] parseMajorAndMinorVersions(String arg0, String arg1) {
        try {
            var majorVersion = Integer.parseInt(arg0);
            var minorVersion = Integer.parseInt(arg1);
            return new int[]{majorVersion, minorVersion};
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(INVALID_PROTOCOL_VERSION + ": " + arg0 + "." + arg1);
        }
    }

    public static HttpVersion of(int majorVersion) {
        if (majorVersion <= 0) {
            throw new IllegalArgumentException(INVALID_PROTOCOL_VERSION + ": " + majorVersion);
        }

        return Arrays.stream(values())
                .filter(httpVersion -> httpVersion.majorVersion == majorVersion && httpVersion.minorVersion == -1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PROTOCOL_VERSION + ": " + majorVersion));
    }

    public static HttpVersion of(int majorVersion, int minorVersion) {
        if (majorVersion <= 0 || minorVersion < 0) {
            throw new IllegalArgumentException(INVALID_PROTOCOL_VERSION + ": " + majorVersion + "." + minorVersion);
        }

        return Arrays.stream(values())
                .filter(httpVersion -> httpVersion.majorVersion == majorVersion)
                .filter(httpVersion -> (httpVersion.minorVersion == minorVersion) || (httpVersion.minorVersion == -1 && minorVersion == 0))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PROTOCOL_VERSION + ": " + majorVersion + "." + minorVersion));
    }

    @Override
    public String toString() {
        var value = PROTOCOL + "/" + majorVersion;

        if (minorVersion == -1) {
            return value;
        }

        return value + "." + minorVersion;
    }
}
