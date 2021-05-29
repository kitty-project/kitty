package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Body;
import com.julianjupiter.kitty.http.message.Cookie;
import com.julianjupiter.kitty.http.message.Header;
import com.julianjupiter.kitty.http.message.Message;

/**
 * @author Julian Jupiter
 */
abstract class KittyMessage implements Message {
    protected KittyHttpHeaders kittyHttpHeaders;
    protected KittyHttpCookies kittyHttpCookies;
    protected Body body;

    protected KittyMessage() {
        this(KittyHttpHeaders.create(), KittyHttpCookies.create(), null);
    }

    protected KittyMessage(KittyHttpHeaders kittyHttpHeaders, KittyHttpCookies kittyHttpCookies, Body body) {
        this.kittyHttpHeaders = kittyHttpHeaders;
        this.kittyHttpCookies = kittyHttpCookies;
        this.body = body;
    }

    @Override
    public Header header(String name) {
        return this.kittyHttpHeaders.get(name)
                .orElse(null);
    }

    @Override
    public Header[] headers() {
        return this.kittyHttpHeaders.get().toArray(new Header[0]);
    }

    @Override
    public Cookie cookie(String name) {
        return this.kittyHttpCookies.get(name)
                .orElse(null);
    }

    @Override
    public Cookie[] cookies() {
        return this.kittyHttpCookies.get().toArray(new Cookie[0]);
    }

    @Override
    public Body body() {
        return body;
    }
}
