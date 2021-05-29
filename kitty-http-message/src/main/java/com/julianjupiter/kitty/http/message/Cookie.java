package com.julianjupiter.kitty.http.message;

import com.julianjupiter.kitty.http.message.util.CookieSameSite;

/**
 * @author Julian Jupiter
 */
public interface Cookie {
    String name();

    String value();

    String domain();

    String path();

    long maxAge();

    boolean secure();

    boolean httpOnly();

    CookieSameSite sameSite();
}
