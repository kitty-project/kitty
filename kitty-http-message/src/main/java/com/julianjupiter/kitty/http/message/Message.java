package com.julianjupiter.kitty.http.message;

import com.julianjupiter.kitty.http.message.util.HttpVersion;

/**
 * @author Julian Jupiter
 */
public interface Message {
    HttpVersion version();

    Header header(String name);

    Header[] headers();

    Cookie cookie(String name);

    Cookie[] cookies();

    Body body();
}
