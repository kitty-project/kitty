package com.julianjupiter.kitty;

/**
 * @author Julian Jupiter
 */
class KittyConfiguration implements Configuration {
    private ServerConfiguration serverConfiguration;

    public KittyConfiguration(ServerConfiguration serverConfiguration) {
        this.serverConfiguration = serverConfiguration;
    }

    @Override
    public ServerConfiguration server() {
        return serverConfiguration;
    }

    @Override
    public Configuration server(ServerConfiguration serverConfiguration) {
        this.serverConfiguration = serverConfiguration;
        return this;
    }
}
