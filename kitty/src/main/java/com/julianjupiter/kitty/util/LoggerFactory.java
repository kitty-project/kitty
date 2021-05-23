package com.julianjupiter.kitty.util;

/**
 * @author Julian Jupiter
 */
public class LoggerFactory {
    private LoggerFactory() {
    }

    public static System.Logger getLogger(Class<?> cls) {
        return System.getLogger(cls.getName());
    }
}
