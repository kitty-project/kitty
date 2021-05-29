package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Cookie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Julian Jupiter
 */
final class KittyHttpCookies {
    private final Set<Cookie> cookieSet = new HashSet<>();

    private KittyHttpCookies() {
    }

    public static KittyHttpCookies create() {
        return new KittyHttpCookies();
    }

    public KittyHttpCookies add(String name, String value) {
        this.cookieSet.add(CookieBuilder.builder(name, value).build());
        return this;
    }

    public KittyHttpCookies add(Cookie cookie) {
        this.cookieSet.add(cookie);
        return this;
    }

    public KittyHttpCookies add(Cookie[] cookies) {
        var collection = Arrays.stream(cookies).collect(Collectors.toUnmodifiableSet());
        this.cookieSet.addAll(collection);
        return this;
    }

    public Set<Cookie> get() {
        return this.cookieSet;
    }

    public Optional<Cookie> get(String name) {
        return this.cookieSet.stream()
                .filter(cookie -> cookie.name().equals(name))
                .findFirst();
    }
}
