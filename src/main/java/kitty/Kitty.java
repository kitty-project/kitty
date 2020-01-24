package kitty;

import java.util.function.Function;

public interface Kitty {
    static Kitty create(Function<Router, Router> router) {
        return new WebServer(router.apply(new RouterImpl()));
    }

    Kitty run();
}
