package kitty;

import java.util.Collections;
import java.util.Map;

class NegativeResult implements MatchResult {
    private NegativeResult() {

    }

    public static NegativeResult create() {
        return new NegativeResult();
    }

    @Override
    public boolean matches() {
        return false;
    }

    @Override
    public Map<String, String> params() {
        return Collections.emptyMap();
    }

    @Override
    public String param(String name) {
        return null;
    }
}
