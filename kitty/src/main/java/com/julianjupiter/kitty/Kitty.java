package com.julianjupiter.kitty;

/**
 * @author Julian Jupiter
 */
public sealed interface Kitty extends RouterProxy<Kitty> permits KittyFramework {
    static Kitty meow() {
        return new KittyFramework();
    }

    static Kitty meow(Configuration configuration) {
        return new KittyFramework(configuration);
    }

    void run();

    void run(Runnable runnable);
}
