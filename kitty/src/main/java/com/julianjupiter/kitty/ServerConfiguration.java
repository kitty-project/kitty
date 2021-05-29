package com.julianjupiter.kitty;

import com.julianjupiter.kitty.util.Constants;
import com.julianjupiter.kitty.util.KittyUtil;

import java.util.UUID;

/**
 * @author Julian Jupiter
 */
public interface ServerConfiguration {

    static ServerConfiguration create() {
        return new KittyServerConfiguration(
                UUID.randomUUID().toString(),
                KittyUtil.Server.hostAddress(),
                Constants.Server.DEFAULT_PORT,
                Constants.Server.DEFAULT_CONTEXT_PATH
        );
    }

    static ServerConfigurationBuilder builder() {
        return ServerConfigurationBuilder.create();
    }

    String name();

    String hostAddress();

    int port();

    String contextPath();

}
