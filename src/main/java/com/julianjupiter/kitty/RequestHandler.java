package com.julianjupiter.kitty;

@FunctionalInterface
public interface RequestHandler {
    Response handle(Request request, Response response);
}