package com.julianjupiter.kitty;

public interface Configuration {
    static Configuration create() {
        return DefaultConfiguration.create();
    }

    static ConfigurationBuilder builder() {
        return ConfigurationBuilder.builder();
    }

    String host();

    int port();

    String contextPath();

    Object jsonMapper();

    ViewResolver viewResolver();
}
