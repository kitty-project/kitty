package com.julianjupiter.kitty;

/**
 * @author Julian Jupiter
 */
public interface Configuration {
    static Configuration create() {
        return new KittyConfiguration(ServerConfiguration.create());
    }

    static ConfigurationBuilder builder() {
        return ConfigurationBuilder.create();
    }

    ServerConfiguration server();

    Configuration server(ServerConfiguration serverConfiguration);

}
