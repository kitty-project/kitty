package com.julianjupiter.kitty;

import java.io.Writer;
import java.util.Map;

public interface ViewResolver {
    ViewResolver prefix(String prefix);

    ViewResolver suffix(String suffix);

    Writer process(String template, Map<String, Object> attributes);
}

