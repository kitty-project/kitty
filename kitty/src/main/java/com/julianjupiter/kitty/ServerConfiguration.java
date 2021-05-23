package com.julianjupiter.kitty;

import java.util.UUID;

/**
 * @author Julian Jupiter
 */
public interface ServerConfiguration {

    static ServerConfiguration create() {
        return new KittyServerConfiguration(
                UUID.randomUUID().toString(),
                Constants.Server.DEFAULT_HOST,
                Constants.Server.DEFAULT_PORT,
                Constants.Server.DEFAULT_CONTEXT_PATH
        );
    }

    static ServerConfigurationBuilder builder() {
        return ServerConfigurationBuilder.create();
    }

    String name();

    String host();

    int port();

    String contextPath();

}
