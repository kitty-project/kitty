package kitty;

public class WebServer implements Kitty, Handler {
    private final Router router;
    private int port = 8080;

    public WebServer(Router router) {
        this.router = router;
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
        router.routes().forEach(route -> System.out.println(route.method() + "\n" + route.path()));
    }
}
