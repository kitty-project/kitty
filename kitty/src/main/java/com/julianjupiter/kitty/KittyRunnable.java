package com.julianjupiter.kitty;

/**
 * @author Julian Jupiter
 */
public interface KittyRunnable {
    void run();

    void run(int port);

    void run(Runnable runnable);

    void run(int port, Runnable runnable);
}
