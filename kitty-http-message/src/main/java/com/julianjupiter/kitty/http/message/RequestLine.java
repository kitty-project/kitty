package com.julianjupiter.kitty.http.message;

import com.julianjupiter.kitty.http.message.util.HttpMethod;
import com.julianjupiter.kitty.http.message.util.HttpVersion;

import java.net.URI;

/**
 * @author Julian Jupiter
 */
public interface RequestLine {
    HttpMethod method();

    URI target();

    HttpVersion version();
}
