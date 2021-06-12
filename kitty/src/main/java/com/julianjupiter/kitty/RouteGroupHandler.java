package com.julianjupiter.kitty;

/**
 * @author Julian Jupiter
 */
@FunctionalInterface
public interface RouteGroupHandler {
    RouteGroup handle(RouteGroupBuilder builder);
}
