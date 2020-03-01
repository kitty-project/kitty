package kitty;

import javax.json.bind.JsonbBuilder;

class DefaultConfiguration implements Configuration {
    private static final boolean SSL = System.getProperty("ssl") != null;
    private static final int PORT = Integer.parseInt(System.getProperty("port", SSL ? "8443" : "7000"));

    static Configuration create() {
        return new DefaultConfiguration();
    }

    @Override
    public String host() {
        return "localhost";
    }

    @Override
    public int port() {
        return PORT;
    }

    @Override
    public String contextPath() {
        return "/";
    }

    @Override
    public Object jsonMapper() {
        return JsonbBuilder.create();
    }
}
