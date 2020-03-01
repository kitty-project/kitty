package kitty;

import java.util.Map;

public interface MatchResult {
    boolean matches();

    Map<String, String> params();

    String param(String name);
}