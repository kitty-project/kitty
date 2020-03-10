package com.julianjupiter.kitty;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.StaticHttpHandler;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

final class GrizzlyHttpServer {
    private static final Logger LOGGER = Logger.getLogger(GrizzlyHttpServer.class.getName());

    public static void run(Configuration configuration, Set<Route> routes) {
        final HttpServer server = new HttpServer();
        NetworkListener networkListener = new NetworkListener("com.julianjupiter.kitty-listener", configuration.host(), configuration.port());
        server.addListener(networkListener);
        ServerConfiguration config = server.getServerConfiguration();
        HttpHandler handler = new GrizzlyHttpHandler(configuration, routes);
        config.addHttpHandler(handler, "/**");
        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("src/main/resources");
        config.addHttpHandler(staticHttpHandler, "*.css", "*.js", "*.png", "*.jpg", "*.jpeg", "*.svg");
        try {
            server.start();
            System.in.read();
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
        } finally {
            server.shutdownNow();
        }
    }
}