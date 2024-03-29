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
    private final Router router;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$s] %5$s %n");
    }

    KittyFramework() {
        this(Configuration.create(), Router.create());
    }

    KittyFramework(Configuration configuration) {
        this(configuration, Router.create());
    }

    KittyFramework(Router router) {
        this(Configuration.create(), router);
    }

    KittyFramework(Configuration configuration, Router router) {
        this.configuration = configuration;
        this.router = router;
    }

    @Override
    public void run() throws InterruptedException {
        try {
            new NettyHttpServer(this.configuration, this.router.routes()).run();
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
        this.router.any(path, handler);
        return this;
    }

    @Override
    public Kitty any(String path, ContextHandler handler) {
        this.router.any(path, handler);
        return this;
    }

    @Override
    public Kitty anyOf(Set<HttpMethod> methods, String path, RequestHandler handler) {
        this.router.anyOf(methods, path, handler);
        return this;
    }

    @Override
    public Kitty anyOf(Set<HttpMethod> methods, String path, ContextHandler handler) {
        this.router.anyOf(methods, path, handler);
        return this;
    }

    @Override
    public Kitty delete(String path, RequestHandler handler) {
        this.router.delete(path, handler);
        return this;
    }

    @Override
    public Kitty delete(String path, ContextHandler handler) {
        this.router.delete(path, handler);
        return this;
    }

    @Override
    public Kitty get(String path, RequestHandler handler) {
        this.router.get(path, handler);
        return this;
    }

    @Override
    public Kitty get(String path, ContextHandler handler) {
        this.router.get(path, handler);
        return this;
    }

    @Override
    public Kitty head(String path, RequestHandler handler) {
        this.router.any(path, handler);
        return this;
    }

    @Override
    public Kitty head(String path, ContextHandler handler) {
        this.router.head(path, handler);
        return this;
    }

    @Override
    public Kitty options(String path, RequestHandler handler) {
        this.router.options(path, handler);
        return this;
    }

    @Override
    public Kitty options(String path, ContextHandler handler) {
        this.router.options(path, handler);
        return this;
    }

    @Override
    public Kitty patch(String path, RequestHandler handler) {
        this.router.patch(path, handler);
        return this;
    }

    @Override
    public Kitty patch(String path, ContextHandler handler) {
        this.router.patch(path, handler);
        return this;
    }

    @Override
    public Kitty post(String path, RequestHandler handler) {
        this.router.post(path, handler);
        return this;
    }

    @Override
    public Kitty post(String path, ContextHandler handler) {
        this.router.post(path, handler);
        return this;
    }

    @Override
    public Kitty put(String path, RequestHandler handler) {
        this.router.put(path, handler);
        return this;
    }

    @Override
    public Kitty put(String path, ContextHandler handler) {
        this.router.put(path, handler);
        return this;
    }

    @Override
    public Kitty trace(String path, RequestHandler handler) {
        this.router.trace(path, handler);
        return this;
    }

    @Override
    public Kitty trace(String path, ContextHandler handler) {
        this.router.trace(path, handler);
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
