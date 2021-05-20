package com.julianjupiter.kitty.http.message;

import java.net.URI;

/**
 * @author Julian Jupiter
 */
public interface RequestLine {

    HttpMethod method();

    URI target();

    HttpVersion version();

}
