package com.julianjupiter.kitty;

/**
 * @author Julian Jupiter
 */
public sealed interface Kitty extends KittyRunnable, RouterProxy<Kitty> permits KittyFramework {
    static Kitty meow() {
        return new KittyFramework();
    }

    static Kitty meow(Configuration configuration) {
        return new KittyFramework(configuration);
    }

    static Kitty meow(RouteCollector routeCollector) {
        return new KittyFramework(routeCollector);
    }

    static Kitty meow(Configuration configuration, RouteCollector routeCollector) {
        return new KittyFramework(configuration, routeCollector);
    }
}
