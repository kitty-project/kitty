package com.julianjupiter.kitty.util;

/**
 * @author Julian Jupiter
 */
public class Constants {

    // String Pool
    public static class Characters {
        public static final String FORWARD_SLASH = "/";

        private Characters() {
        }
    }

    // Server
    public static class Server {
        public static final String DEFAULT_HOST_ADDRESS = "127.0.0.1";
        public static final int DEFAULT_PORT = 8080;
        public static final String DEFAULT_CONTEXT_PATH = "/";

        private Server() {
        }
    }

    private Constants() {
    }
}
