package kitty;

public interface Configuration {
    static Configuration create() {
        return DefaultConfiguration.create();
    }

    static ConfigurationBuilder builder() {
        return ConfigurationBuilder.builder();
    }

    static ConfigurationBuilder builder(Configuration configuration) {
        return ConfigurationBuilder.builder(configuration);
    }

    String host();

    int port();

    String contextPath();

    Object jsonMapper();

    ViewResolver viewResolver();
}
