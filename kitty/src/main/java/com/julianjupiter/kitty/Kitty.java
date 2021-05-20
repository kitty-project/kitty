package com.julianjupiter.kitty;

/**
 * @author Julian Jupiter
 */
public sealed interface Kitty extends RouterProxy permits DefaultKitty {
    static Kitty meow() {
        return new DefaultKitty();
    }

    void run();

    void run(Runnable runnable);
}
