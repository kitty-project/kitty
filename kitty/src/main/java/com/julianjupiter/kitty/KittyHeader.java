package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Header;

/**
 * @author Julian Jupiter
 */
record KittyHeader(String name, String value) implements Header {
    @Override
    public String toString() {
        return name + ": " + value;
    }
}
