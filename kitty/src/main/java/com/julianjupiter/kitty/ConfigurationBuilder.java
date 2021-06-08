package com.julianjupiter.kitty;

import java.util.Objects;

/**
 * @author Julian Jupiter
 */
public final class ConfigurationBuilder implements Builder<Configuration> {
    private ServerConfiguration serverConfiguration = ServerConfiguration.create();

    private ConfigurationBuilder() {
    }

    public static ConfigurationBuilder create() {
        return new ConfigurationBuilder();
    }

    public ConfigurationBuilder server(ServerConfiguration serverConfiguration) {
        Objects.requireNonNull(serverConfiguration, "serverConfiguration cannot be null");

        this.serverConfiguration = serverConfiguration;
        return this;
    }

    @Override
    public Configuration build() {
        return new KittyConfiguration(this.serverConfiguration);
    }
}
