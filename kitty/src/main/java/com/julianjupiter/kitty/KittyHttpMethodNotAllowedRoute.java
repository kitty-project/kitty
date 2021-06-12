package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpMethod;
import com.julianjupiter.kitty.http.message.util.HttpStatus;
import com.julianjupiter.kitty.http.message.util.ResponseHeaderName;
import com.julianjupiter.kitty.util.Constants;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Julian Jupiter
 */
final class KittyHttpMethodNotAllowedRoute implements Route {
    private final HttpMethod[] methods;

    public KittyHttpMethodNotAllowedRoute(HttpMethod[] methods) {
        this.methods = methods;
    }

    @Override
    public HttpMethod method() {
        return null;
    }

    @Override
    public String path() {
        return null;
    }

    @Override
    public Handler handler() {
        return ((request, response) -> response
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(ResponseHeaderName.ALLOW.toString(), this.allowValue())
        );
    }

    private String allowValue() {
        return Arrays.stream(methods)
                .map(HttpMethod::name)
                .collect(Collectors.joining(Constants.Characters.COMMA));
    }
}
