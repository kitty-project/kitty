package com.julianjupiter.kitty;

/**
 * @author Julian Jupiter
 */
public sealed interface Kitty extends RouterProxy<Kitty> permits KittyFramework {
    static Kitty meow() {
        return new KittyFramework();
    }

    void run();

    void run(Runnable runnable);
}
