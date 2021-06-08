package com.julianjupiter.kitty;

import java.util.Map;

interface MatchResult {
    boolean matches();

    Map<String, String> params();

    String param(String name);
}