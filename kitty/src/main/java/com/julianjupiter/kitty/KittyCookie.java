package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Cookie;
import com.julianjupiter.kitty.http.message.util.CookieHeaderNames;

/**
 * @author Julian Jupiter
 */
record KittyCookie(
        String name,
        String value,
        String domain,
        String path,
        long maxAge,
        boolean secure,
        boolean httpOnly,
        CookieHeaderNames.SameSite sameSite) implements Cookie {
}
