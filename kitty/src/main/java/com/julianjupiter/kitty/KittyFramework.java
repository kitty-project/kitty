package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpMethod;
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
    private final RouterBuilder routerBuilder;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$s] %5$s %n");
    }

    KittyFramework() {
        this(Configuration.create());
    }

    KittyFramework(Configuration configuration) {
        this(configuration, RouterBuilder.create().build());
    }

    KittyFramework(Router router) {
        this(Configuration.create(), router);
    }

    KittyFramework(Configuration configuration, Router router) {
        this.configuration = configuration;
        this.routerBuilder = RouterBuilder.create(router);
    }

    @Override
    public void run() throws InterruptedException {
        try {
            new NettyHttpServer(this.configuration, this.routerBuilder.build().routes()).run();
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
    public Kitty route(HttpMethod method, String path, RequestHandler handler) {
        this.routerBuilder.route(method, path, handler);
        return this;
    }

    @Override
    public Kitty route(HttpMethod method, String path, ContextHandler handler) {
        this.routerBuilder.route(method, path, handler);
        return this;
    }

    @Override
    public Kitty any(String path, RequestHandler handler) {
        this.routerBuilder.any(path, handler);
        return this;
    }

    @Override
    public Kitty any(String path, ContextHandler handler) {
        this.routerBuilder.any(path, handler);
        return this;
    }

    @Override
    public Kitty anyOf(Set<HttpMethod> methods, String path, RequestHandler handler) {
        this.routerBuilder.anyOf(methods, path, handler);
        return this;
    }

    @Override
    public Kitty anyOf(Set<HttpMethod> methods, String path, ContextHandler handler) {
        this.routerBuilder.anyOf(methods, path, handler);
        return this;
    }

    @Override
    public Kitty delete(String path, RequestHandler handler) {
        this.routerBuilder.delete(path, handler);
        return this;
    }

    @Override
    public Kitty delete(String path, ContextHandler handler) {
        this.routerBuilder.delete(path, handler);
        return this;
    }

    @Override
    public Kitty get(String path, RequestHandler handler) {
        this.routerBuilder.get(path, handler);
        return this;
    }

    @Override
    public Kitty get(String path, ContextHandler handler) {
        this.routerBuilder.get(path, handler);
        return this;
    }

    @Override
    public Kitty head(String path, RequestHandler handler) {
        this.routerBuilder.head(path, handler);
        return this;
    }

    @Override
    public Kitty head(String path, ContextHandler handler) {
        this.routerBuilder.head(path, handler);
        return this;
    }

    @Override
    public Kitty options(String path, RequestHandler handler) {
        this.routerBuilder.options(path, handler);
        return this;
    }

    @Override
    public Kitty options(String path, ContextHandler handler) {
        this.routerBuilder.options(path, handler);
        return this;
    }

    @Override
    public Kitty patch(String path, RequestHandler handler) {
        this.routerBuilder.patch(path, handler);
        return this;
    }

    @Override
    public Kitty patch(String path, ContextHandler handler) {
        this.routerBuilder.patch(path, handler);
        return this;
    }

    @Override
    public Kitty post(String path, RequestHandler handler) {
        this.routerBuilder.post(path, handler);
        return this;
    }

    @Override
    public Kitty post(String path, ContextHandler handler) {
        this.routerBuilder.post(path, handler);
        return this;
    }

    @Override
    public Kitty put(String path, RequestHandler handler) {
        this.routerBuilder.put(path, handler);
        return this;
    }

    @Override
    public Kitty put(String path, ContextHandler handler) {
        this.routerBuilder.put(path, handler);
        return this;
    }

    @Override
    public Kitty trace(String path, RequestHandler handler) {
        this.routerBuilder.trace(path, handler);
        return this;
    }

    @Override
    public Kitty trace(String path, ContextHandler handler) {
        this.routerBuilder.trace(path, handler);
        return this;
    }

    @Override
    public Kitty group(String path, RouteGroupHandler handler) {
        this.routerBuilder.group(path, handler);
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
