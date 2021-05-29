package com.julianjupiter.kitty.http.message.util;

import java.util.Arrays;

/**
 * @author Julian Jupiter
 */
public enum CookieSameSite {
    LAX("Lax"),
    STRICT("Strict"),
    NAME("None");

    private final String name;

    CookieSameSite(String name) {
        this.name = name;
    }

    public static CookieSameSite of(String name) {
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
