package com.julianjupiter.kitty;

import java.util.List;
import java.util.Map;

/**
 * @author Julian Jupiter
 */
public sealed interface Router extends RouteCollectorProxy<Router> permits KittyRouter {
    static Router create() {
        return new KittyRouter();
    }

    Map<String, List<Route>> routes();
}
