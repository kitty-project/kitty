package com.julianjupiter.kitty;

public interface Route {
    HttpMethod method();

    String path();

    RequestHandler handler();
}
