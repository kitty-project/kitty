package ex;

import com.julianjupiter.kitty.ViewResolver;

import java.io.Writer;
import java.util.Map;

public class JTwigViewResolver implements ViewResolver {
    private String prefix;
    private String suffix;

    @Override
    public ViewResolver prefix(String prefix) {
        this.prefix = "templates";
        return this;
    }

    @Override
    public ViewResolver suffix(String suffix) {
        this.suffix = ".twig";
        return this;
    }

    @Override
    public Writer process(String template, Map<String, Object> attributes) {
        return null;
    }
}
