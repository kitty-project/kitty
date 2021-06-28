package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.PathParam;

/**
 * @author Julian Jupiter
 */
record KittyPathParam(String name, String asString) implements PathParam {
    @Override
    public int asInt() {
        try {
            return Integer.parseInt(this.asString);
        } catch (NumberFormatException exception) {
            return 0;
        }
    }

    @Override
    public long asLong() {
        try {
            return Long.parseLong(this.asString);
        } catch (NumberFormatException exception) {
            return 0L;
        }
    }
}
