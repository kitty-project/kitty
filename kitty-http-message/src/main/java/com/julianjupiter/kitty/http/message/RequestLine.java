package com.julianjupiter.kitty.http;

import com.julianjupiter.kitty.http.util.HttpMethod;
import com.julianjupiter.kitty.http.util.HttpVersion;

import java.net.URI;

/**
 * @author Julian Jupiter
 */
public interface RequestLine {

    HttpMethod method();

    URI target();

    HttpVersion version();

}
