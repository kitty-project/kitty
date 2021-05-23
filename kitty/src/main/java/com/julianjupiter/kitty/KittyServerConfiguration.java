package com.julianjupiter.kitty;

/**
 * @author Julian Jupiter
 */
record KittyServerConfiguration(String name, String host, int port, String contextPath) implements ServerConfiguration {
}
