package com.julianjupiter.kitty;

class ConfigurationImpl implements Configuration {
    private final ConfigurationBuilder configurationBuilder;

    ConfigurationImpl(ConfigurationBuilder configurationBuilder) {
        this.configurationBuilder = configurationBuilder;
    }

    @Override
    public String host() {
        return configurationBuilder.host();
    }

    @Override
    public int port() {
        return configurationBuilder.port();
    }

    @Override
    public String contextPath() {
        return configurationBuilder.contextPath();
    }

    @Override
    public Object jsonMapper() {
        return configurationBuilder.jsonMapper();
    }

    @Override
    public ViewResolver viewResolver() {
        return configurationBuilder.viewResolver();
    }
}
