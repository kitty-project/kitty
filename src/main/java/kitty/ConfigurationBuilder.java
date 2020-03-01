package kitty;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.Objects;
import java.util.Optional;

public class ConfigurationBuilder {
    private static final boolean SSL = System.getProperty("ssl") != null;
    private static final int PORT = Integer.parseInt(System.getProperty("port", SSL ? "8443" : "7000"));
    private String host = "localhost";
    private int port = PORT;
    private String contextPath = "/";
    private Object jsonMapper = JsonbBuilder.create();
    private ViewResolver viewResolver = PebbleViewResolver.create();

    private ConfigurationBuilder() {

    }

    public static ConfigurationBuilder builder() {
        return new ConfigurationBuilder();
    }

    public static ConfigurationBuilder builder(Configuration configuration) {
        return new ConfigurationBuilder()
                .host(configuration.host())
                .port(configuration.port())
                .contextPath(configuration.contextPath())
                .jsonMapper(configuration.jsonMapper());
    }

    public String host() {
        return host;
    }

    public ConfigurationBuilder host(String host) {
        Objects.requireNonNull(host, "Parameter 'host' is null!");

        this.host = host;
        return this;
    }

    public int port() {
        return port;
    }

    public ConfigurationBuilder port(int port) {
        this.port = port;
        return this;
    }

    public String contextPath() {
        return contextPath;
    }

    public ConfigurationBuilder contextPath(String contextPath) {
        Objects.requireNonNull(contextPath, "Parameter 'contextPath' is null!");

        String contextPathWithLeadingForwardSlash = Optional.of(contextPath)
                .filter(cp -> cp.startsWith("/"))
                .orElse("/" + contextPath);
        String finalContextPath = Optional.of(contextPathWithLeadingForwardSlash)
                .filter(cp -> cp.length() == 1 || !cp.endsWith("/"))
                .orElse(contextPathWithLeadingForwardSlash.substring(0, contextPathWithLeadingForwardSlash.length() - 1));

        this.contextPath = finalContextPath;
        return this;
    }

    public Object jsonMapper() {
        return jsonMapper;
    }

    public ViewResolver viewResolver() {
        return viewResolver;
    }

    public ConfigurationBuilder viewResolver(ViewResolver viewResolver) {
        this.viewResolver = viewResolver;
        return this;
    }

    public ConfigurationBuilder jsonMapper(Object jsonMapper) {
        this.jsonMapper = jsonMapper;
        return this;
    }

    public Configuration build() {
        return new ConfigurationImpl(this);
    }
}
