package kitty;

import java.util.HashSet;
import java.util.function.Function;

public interface Kitty {

    static Kitty create(Function<Router, Router> router) {
        return create(Configuration.create(), router);
    }

    static Kitty create(Configuration configuration, Function<Router, Router> router) {
        return new KittyImpl(configuration, router.apply(new RouterImpl(configuration.contextPath())));
    }

    Kitty run();

    Kitty run(int port);

    Kitty run(int port, Handler handler);
}
