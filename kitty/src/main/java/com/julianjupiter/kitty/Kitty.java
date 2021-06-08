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

    static Kitty meow(Router router) {
        return new KittyFramework(router);
    }

    static Kitty meow(Configuration configuration, Router router) {
        return new KittyFramework(configuration, router);
    }
}
