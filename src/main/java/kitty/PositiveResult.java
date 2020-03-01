package kitty;

import java.util.Collections;
import java.util.Map;

class PositiveResult implements MatchResult {
    private final Map<String, String> params;

    private PositiveResult(Map<String, String> params) {
        this.params = params;
    }

    public static PositiveResult create(Map<String, String> params) {
        if (params != null) {
            return new PositiveResult(params);
        }

        return new PositiveResult(Collections.emptyMap());
    }

    @Override
    public boolean matches() {
        return true;
    }

    @Override
    public Map<String, String> params() {
        return params;
    }

    @Override
    public String param(String name) {
        return params.get(name);
    }
}
