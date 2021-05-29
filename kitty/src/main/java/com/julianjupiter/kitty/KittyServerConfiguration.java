package com.julianjupiter.kitty;

/**
 * @author Julian Jupiter
 */
record KittyServerConfiguration(String name, String hostAddress, int port, String contextPath) implements ServerConfiguration {
}
