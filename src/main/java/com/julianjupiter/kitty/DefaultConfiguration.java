package com.julianjupiter.kitty;

import javax.json.bind.JsonbBuilder;

class DefaultConfiguration implements Configuration {
    private static final String HOST = "localhost";
    private static final boolean SSL = System.getProperty("ssl") != null;
    private static final int PORT = Integer.parseInt(System.getProperty("port", SSL ? "8443" : "7000"));
    private static final String CONTEXT_PATH = "/";
    private static final Object JSON_MAPPER = JsonbBuilder.create();
    private static final ViewResolver VIEW_RESOLVER = PebbleViewResolver.create();

    static Configuration create() {
        return new DefaultConfiguration();
    }

    @Override
    public String host() {
        return HOST;
    }

    @Override
    public int port() {
        return PORT;
    }

    @Override
    public String contextPath() {
        return CONTEXT_PATH;
    }

    @Override
    public Object jsonMapper() {
        return JSON_MAPPER;
    }

    @Override
    public ViewResolver viewResolver() {
        return VIEW_RESOLVER;
    }
}
