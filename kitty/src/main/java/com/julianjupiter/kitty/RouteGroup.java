package com.julianjupiter.kitty;

import java.util.List;

/**
 * @author Julian Jupiter
 */
public sealed interface RouteGroup extends RouterProxy<RouteGroup> permits KittyRouteGroup {
    List<KittyRouteGroup.RouteGroupRoute> routes();
}
