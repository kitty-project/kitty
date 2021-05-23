package com.julianjupiter.kitty;

import java.util.List;
import java.util.Map;

/**
 * @author Julian Jupiter
 */
public sealed interface RouteCollector extends RouterProxy<RouteCollector> permits KittyRouteCollector {
    static RouteCollector create() {
        return new KittyRouteCollector();
    }

    Map<String, List<Route>> routes();
}
