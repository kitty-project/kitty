package com.julianjupiter.kitty.http.message;

import java.util.OptionalInt;
import java.util.OptionalLong;

/**
 * @author Julian Jupiter
 */
public interface PathParam {
    String name();

    String asString();

    OptionalInt asInt();

    OptionalLong asDouble();
}
