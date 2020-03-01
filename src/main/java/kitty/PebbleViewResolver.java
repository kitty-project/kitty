package kitty;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PebbleViewResolver implements ViewResolver {
    private static final Logger LOGGER = Logger.getLogger(PebbleViewResolver.class.getName());
    private String prefix = "templates";
    private String suffix = ".html";

    private PebbleViewResolver() {

    }

    public static ViewResolver create() {
        return new PebbleViewResolver();
    }

    @Override
    public ViewResolver prefix(String prefix) {
        this.prefix = prefix;

        return this;
    }

    @Override
    public ViewResolver suffix(String suffix) {
        this.suffix = suffix;

        return this;
    }

    @Override
    public Writer process(String template, Map<String, Object> attributes) {
        PebbleEngine engine = new PebbleEngine.Builder().build();
        PebbleTemplate compiledTemplate = engine.getTemplate(this.prefix + "/" + template + suffix);
        Writer writer = new StringWriter();
        try {
            if (attributes != null) {
                compiledTemplate.evaluate(writer, attributes);
            } else {
                compiledTemplate.evaluate(writer);
            }
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, () -> exception.getMessage());
            throw new RuntimeException(exception.getMessage(), exception);
        }

        return writer;
    }
}

