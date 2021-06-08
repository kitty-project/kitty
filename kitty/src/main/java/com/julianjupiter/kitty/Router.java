package com.julianjupiter.kitty;

import java.util.List;
import java.util.Map;

/**
 * @author Julian Jupiter
 */
public sealed interface Router extends RouterProxy<Router> permits KittyRouter {
    static RouterBuilder builder() {
        return RouterBuilder.builder();
    }

    Map<String, List<Route>> routes();
}
