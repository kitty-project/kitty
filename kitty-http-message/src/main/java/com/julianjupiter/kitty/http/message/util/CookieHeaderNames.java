package com.julianjupiter.kitty.http.message.util;

import java.util.Arrays;

/**
 * @author Julian Jupiter
 */
public enum CookieHeaderNames {
    PATH("Path"),
    EXPIRES("Expires"),
    MAX_AGE("Max-Age"),
    DOMAIN("Domain"),
    SECURE("Secure"),
    HTTPONLY("HTTPOnly"),
    SAMESITE("SameSite");

    private final String name;
    CookieHeaderNames(String name) {
        this.name = name;
    }

    public enum SameSite {
        LAX("Lax"),
        STRICT("Strict"),
        NAME("None");

        private final String name;
        SameSite(String name) {
            this.name = name;
        }

        public static SameSite of(String name) {
            if (name != null) {
                return Arrays.stream(values())
                        .filter(sameSite -> sameSite.name.equalsIgnoreCase(name))
                        .findFirst()
                        .orElse(null);
            }

            return null;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
