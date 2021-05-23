package com.julianjupiter.kitty.http.message;

import java.net.URI;

/**
 * @author Julian Jupiter
 */
public interface Request extends Message {

    HttpMethod method();

    URI target();

    RequestLine requestLine();

    QueryParam[] queryParams();

}
