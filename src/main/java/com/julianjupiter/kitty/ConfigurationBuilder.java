package com.julianjupiter.kitty;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigurationBuilder {
    private static final Logger LOGGER = Logger.getLogger(ConfigurationBuilder.class.getName());
    private String host;
    private int port;
    private String contextPath;
    private Object jsonMapper;
    private ViewResolver viewResolver;

    private ConfigurationBuilder(Configuration configuration) {
        this.host = configuration.host();
        this.port = configuration.port();
        this.contextPath = configuration.contextPath();
        this.jsonMapper = configuration.jsonMapper();
        this.viewResolver = configuration.viewResolver();
    }

    public static ConfigurationBuilder builder() {
        return new ConfigurationBuilder(DefaultConfiguration.create());
    }

    public String host() {
        return host;
    }

    public ConfigurationBuilder host(String host) {
        Objects.requireNonNull(host, "Parameter 'host' is null!");

        this.host = host;
        return this;
    }

    public int port() {
        return port;
    }

    public ConfigurationBuilder port(int port) {
        if (port < 0) {
            throw new IllegalArgumentException("Parameter 'port' cannot be less than 0!");
        } else if (port == 0) {
            try (ServerSocket socket = new ServerSocket(0)) {
                socket.setReuseAddress(true);
                this.port = socket.getLocalPort();
            } catch (IOException exception) {
                LOGGER.log(Level.WARNING, () -> "No available port; using default port " + port + "!");
            }
        } else {
            this.port = port;
        }

        return this;
    }

    public String contextPath() {
        return contextPath;
    }

    public ConfigurationBuilder contextPath(String contextPath) {
        Objects.requireNonNull(contextPath, "Parameter 'contextPath' is null!");

        String contextPathWithLeadingForwardSlash = Optional.of(contextPath)
                .filter(cp -> cp.startsWith("/"))
                .orElse("/" + contextPath);
        String finalContextPath = Optional.of(contextPathWithLeadingForwardSlash)
                .filter(cp -> cp.length() == 1 || !cp.endsWith("/"))
                .orElse(contextPathWithLeadingForwardSlash.substring(0, contextPathWithLeadingForwardSlash.length() - 1));

        this.contextPath = finalContextPath;
        return this;
    }

    public Object jsonMapper() {
        return jsonMapper;
    }

    public ViewResolver viewResolver() {
        return viewResolver;
    }

    public ConfigurationBuilder viewResolver(ViewResolver viewResolver) {
        this.viewResolver = viewResolver;
        return this;
    }

    public ConfigurationBuilder jsonMapper(Object jsonMapper) {
        this.jsonMapper = jsonMapper;
        return this;
    }

    public Configuration build() {
        return new ConfigurationImpl(this);
    }
}
