package com.julianjupiter.kitty;

import java.util.Set;

class KittyImpl implements Kitty, Handler {
    private final Configuration configuration;
    private final Set<Pair<PathMatcher, Route>> routes;
    private int port = -1;

    public KittyImpl(Configuration configuration, Router router) {
        this.configuration = configuration;
        this.routes = router.routes();
    }

    @Override
    public Kitty run() {
        this.handle();
        return this;
    }

    @Override
    public Kitty run(int port) {
        this.port = port;
        this.handle();
        return this;
    }

    @Override
    public Kitty run(int port, Handler handler) {
        this.port = port;
        handler.handle();
        this.handle();
        return this;
    }

    @Override
    public void handle() {
        if (port != -1) {
            GrizzlyHttpServer.run(Configuration.builder().port(port).build(), routes);
        } else {
            GrizzlyHttpServer.run(configuration, routes);
        }
    }
}
