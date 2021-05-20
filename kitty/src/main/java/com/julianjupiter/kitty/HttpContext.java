package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.Response;

/**
 * @author Julian Jupiter
 */
public record HttpContext(Request request, Response response) {
}
