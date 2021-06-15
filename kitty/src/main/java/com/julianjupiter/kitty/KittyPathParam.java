package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.PathParam;

import java.util.OptionalInt;
import java.util.OptionalLong;

/**
 * @author Julian Jupiter
 */
record KittyPathParam(String name, String asString) implements PathParam {
    @Override
    public OptionalInt asInt() {
        try {
            return OptionalInt.of(Integer.parseInt(this.asString));
        } catch (NumberFormatException exception) {
            return OptionalInt.empty();
        }
    }

    @Override
    public OptionalLong asDouble() {
        try {
            return OptionalLong.of(Integer.parseInt(this.asString));
        } catch (NumberFormatException exception) {
            return OptionalLong.empty();
        }
    }
}
