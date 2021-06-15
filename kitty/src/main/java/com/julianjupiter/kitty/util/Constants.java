package com.julianjupiter.kitty.util;

/**
 * @author Julian Jupiter
 */
public class Constants {

    // String Pool
    public static class Characters {
        public static final String COMMA = ",";
        public static final String COLON = ":";
        public static final String CURLY_BRACE_LEFT = "{";
        public static final String CURLY_BRACE_RIGHT = "}";
        public static final String EMPTY_SPACE = "";
        public static final String FORWARD_SLASH = "/";

        private Characters() {
        }
    }

    // Server
    public static class Server {
        public static final String DEFAULT_HOST_ADDRESS = "127.0.0.1";
        public static final int DEFAULT_PORT = 8080;
        public static final String DEFAULT_CONTEXT_PATH = Characters.FORWARD_SLASH;

        private Server() {
        }
    }

    private Constants() {
    }
}
