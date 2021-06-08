package com.julianjupiter.kitty;

import com.julianjupiter.kitty.util.Constants;
import com.julianjupiter.kitty.util.KittyUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Julian Jupiter
 */
public final class ServerConfigurationBuilder implements Builder<ServerConfiguration> {
    private static final System.Logger logger = System.getLogger(ServerConfigurationBuilder.class.getName());
    private String name = UUID.randomUUID().toString();
    private int port = Constants.Server.DEFAULT_PORT;
    private String contextPath = Constants.Server.DEFAULT_CONTEXT_PATH;

    private ServerConfigurationBuilder() {
    }

    public static ServerConfigurationBuilder create() {
        return new ServerConfigurationBuilder();
    }

    public ServerConfigurationBuilder name(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
        return this;
    }

    public ServerConfigurationBuilder port(int port) {
        if (port < 0) {
            throw new IllegalArgumentException("port cannot be negative integer: " + port);
        } else if (port == 0) {
            try (var socket = new ServerSocket(0)) {
                socket.setReuseAddress(true);
                this.port = socket.getLocalPort();
            } catch (IOException exception) {
                logger.log(System.Logger.Level.WARNING, () -> "No port available, using default port: " + port);
            }
        } else {
            this.port = port;
        }
        return this;
    }

    public ServerConfigurationBuilder contextPath(String contextPath) {
        Objects.requireNonNull(contextPath, "contextPath cannot null");

        contextPath = URI.create(contextPath.trim()).getPath();
        var contextPathWithLeadingForwardSlash = Optional.of(contextPath)
                .filter(cp -> cp.startsWith(Constants.Characters.FORWARD_SLASH))
                .orElse(Constants.Characters.FORWARD_SLASH + contextPath);
        this.contextPath = Optional.of(contextPathWithLeadingForwardSlash)
                .filter(cp -> cp.length() == 1 || !cp.endsWith(Constants.Characters.FORWARD_SLASH))
                .orElse(contextPathWithLeadingForwardSlash.substring(0, contextPathWithLeadingForwardSlash.length() - 1));
        return this;
    }

    @Override
    public ServerConfiguration build() {
        return new KittyServerConfiguration(name, KittyUtil.Server.hostAddress(), port, contextPath);
    }
}
