package com.julianjupiter.kitty.http;

import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.Response;

/**
 * @author Julian Jupiter
 */
public interface Context {
    Request request();

    Response response();
}
