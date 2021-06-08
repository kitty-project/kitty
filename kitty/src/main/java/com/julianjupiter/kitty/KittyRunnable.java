package com.julianjupiter.kitty;

/**
 * @author Julian Jupiter
 */
interface KittyRunnable {
    void run() throws InterruptedException;

    void run(int port) throws InterruptedException;

    void run(Runnable runnable) throws InterruptedException;

    void run(int port, Runnable runnable) throws InterruptedException;
}
