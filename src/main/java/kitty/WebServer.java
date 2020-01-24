package kitty;

public class WebServer implements Kitty {
    private final Router router;

    public WebServer(Router router) {
        this.router = router;
    }

    @Override
    public Kitty run() {
        return null;
    }
}
