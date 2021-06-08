package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpMethod;
import com.julianjupiter.kitty.http.message.util.HttpVersion;

import java.net.URI;

/**
 * @author Julian Jupiter
 */
interface RequestLine {
    HttpMethod method();

    URI target();

    HttpVersion version();
}
