package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.CookieSameSite;

/**
 * @author Julian Jupiter
 */
interface CookieSetter<T> {
    T domain(String domain);

    T path(String path);

    T maxAge(long maxAge);

    T secure(boolean secure);

    T httpOnly(boolean httpOnly);

    T sameSite(CookieSameSite sameSite);
}
