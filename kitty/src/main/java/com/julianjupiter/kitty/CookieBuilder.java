package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Cookie;
import com.julianjupiter.kitty.http.message.util.CookieSameSite;

/**
 * @author Julian Jupiter
 */
public class CookieBuilder implements CookieSetter<CookieBuilder> {
    private final String name;
    private final String value;
    private String domain;
    private String path;
    private long maxMage = -9223372036854775808L;
    private boolean secure;
    private boolean httpOnly;
    private CookieSameSite sameSite;

    private CookieBuilder(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static CookieBuilder builder(String name, String value) {
        return new CookieBuilder(name, value);
    }

    @Override
    public CookieBuilder domain(String domain) {
        this.domain = domain;
        return this;
    }

    @Override
    public CookieBuilder path(String path) {
        this.path = path;
        return this;
    }

    @Override
    public CookieBuilder maxAge(long maxAge) {
        this.maxMage = maxAge;
        return this;
    }

    @Override
    public CookieBuilder secure(boolean secure) {
        this.secure = secure;
        return this;
    }

    @Override
    public CookieBuilder httpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
        return this;
    }

    @Override
    public CookieBuilder sameSite(CookieSameSite sameSite) {
        this.sameSite = sameSite;
        return this;
    }

    public Cookie build() {
        return new KittyCookie(
                this.name,
                this.value,
                this.domain,
                this.path,
                this.maxMage,
                this.secure,
                this.httpOnly,
                this.sameSite
        );
    }
}
