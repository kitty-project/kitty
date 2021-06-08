package com.julianjupiter.kitty.http.message;

import com.julianjupiter.kitty.http.message.util.HttpMethod;

import java.net.URI;
import java.util.Optional;

/**
 * @author Julian Jupiter
 */
public interface Request extends Message {
    HttpMethod method();

    URI target();

    Optional<QueryParam> queryParam(String name);

    QueryParam[] queryParams();
}
