package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.HttpMethod;
import com.julianjupiter.kitty.http.server.ContextHandler;
import com.julianjupiter.kitty.http.server.RequestHandler;
import com.julianjupiter.kitty.util.LoggerFactory;

import java.util.Set;

/**
 * @author Julian Jupiter
 */
final class KittyFramework implements Kitty {
    private static final System.Logger logger = LoggerFactory.getLogger(KittyFramework.class);
    private final Configuration configuration;
    private final RouteCollector routeCollector;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$s] %5$s %n");
    }

    KittyFramework() {
        this(Configuration.create(), RouteCollector.create());
    }

    KittyFramework(Configuration configuration) {
        this(configuration, RouteCollector.create());
    }

    KittyFramework(RouteCollector routeCollector) {
        this(Configuration.create(), routeCollector);
    }

    KittyFramework(Configuration configuration, RouteCollector routeCollector) {
        this.configuration = configuration;
        this.routeCollector = routeCollector;
    }

    @Override
    public void run() throws InterruptedException {
        try {
            new NettyHttpServer(this.configuration).run();
        } catch (InterruptedException exception) {
            logger.log(System.Logger.Level.ERROR, "An error was encountered, " + exception.getMessage());
            throw exception;
        }
    }

    @Override
    public void run(int port) throws InterruptedException {
        this.updateServerPort(port);
        this.run();
    }

    @Override
    public void run(Runnable runnable) throws InterruptedException {
        runnable.run();
        this.run();
    }

    @Override
    public void run(int port, Runnable runnable) throws InterruptedException {
        runnable.run();
        this.updateServerPort(port);
        this.run();
    }

    @Override
    public Kitty any(String path, RequestHandler handler) {
        this.routeCollector.any(path, handler);
        return this;
    }

    @Override
    public Kitty any(String path, ContextHandler handler) {
        this.routeCollector.any(path, handler);
        return this;
    }

    @Override
    public Kitty anyOf(Set<HttpMethod> methods, String path, RequestHandler handler) {
        this.routeCollector.anyOf(methods, path, handler);
        return this;
    }

    @Override
    public Kitty anyOf(Set<HttpMethod> methods, String path, ContextHandler handler) {
        this.routeCollector.anyOf(methods, path, handler);
        return this;
    }

    @Override
    public Kitty delete(String path, RequestHandler handler) {
        this.routeCollector.delete(path, handler);
        return this;
    }

    @Override
    public Kitty delete(String path, ContextHandler handler) {
        this.routeCollector.delete(path, handler);
        return this;
    }

    @Override
    public Kitty get(String path, RequestHandler handler) {
        this.routeCollector.get(path, handler);
        return this;
    }

    @Override
    public Kitty get(String path, ContextHandler handler) {
        this.routeCollector.get(path, handler);
        return this;
    }

    @Override
    public Kitty head(String path, RequestHandler handler) {
        this.routeCollector.any(path, handler);
        return this;
    }

    @Override
    public Kitty head(String path, ContextHandler handler) {
        this.routeCollector.head(path, handler);
        return this;
    }

    @Override
    public Kitty options(String path, RequestHandler handler) {
        this.routeCollector.options(path, handler);
        return this;
    }

    @Override
    public Kitty options(String path, ContextHandler handler) {
        this.routeCollector.options(path, handler);
        return this;
    }

    @Override
    public Kitty patch(String path, RequestHandler handler) {
        this.routeCollector.patch(path, handler);
        return this;
    }

    @Override
    public Kitty patch(String path, ContextHandler handler) {
        this.routeCollector.patch(path, handler);
        return this;
    }

    @Override
    public Kitty post(String path, RequestHandler handler) {
        this.routeCollector.post(path, handler);
        return this;
    }

    @Override
    public Kitty post(String path, ContextHandler handler) {
        this.routeCollector.post(path, handler);
        return this;
    }

    @Override
    public Kitty put(String path, RequestHandler handler) {
        this.routeCollector.put(path, handler);
        return this;
    }

    @Override
    public Kitty put(String path, ContextHandler handler) {
        this.routeCollector.put(path, handler);
        return this;
    }

    @Override
    public Kitty trace(String path, RequestHandler handler) {
        this.routeCollector.trace(path, handler);
        return this;
    }

    @Override
    public Kitty trace(String path, ContextHandler handler) {
        this.routeCollector.trace(path, handler);
        return this;
    }

    private void updateServerPort(int port) {
        var existingServerConfig = this.configuration.server();
        var updatedServerConfig = ServerConfiguration.builder()
                .name(existingServerConfig.name())
                .port(port)
                .contextPath(existingServerConfig.contextPath())
                .build();
        this.configuration.server(updatedServerConfig);
    }
}
