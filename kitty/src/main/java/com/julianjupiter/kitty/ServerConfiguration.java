package com.julianjupiter.kitty;

import com.julianjupiter.kitty.util.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * @author Julian Jupiter
 */
public interface ServerConfiguration {

    static ServerConfiguration create() {
        return new KittyServerConfiguration(
                UUID.randomUUID().toString(),
                host(),
                Constants.Server.DEFAULT_PORT,
                Constants.Server.DEFAULT_CONTEXT_PATH
        );
    }

    static ServerConfigurationBuilder builder() {
        return ServerConfigurationBuilder.create();
    }

    String name();

    int port();

    String contextPath();

    static String host() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            LoggerFactory.getLogger(ServerConfiguration.class)
                    .log(System.Logger.Level.WARNING, "Unknown host, using default " + Constants.Server.DEFAULT_HOST);
        }

        return Constants.Server.DEFAULT_HOST;
    }

}
