package com.julianjupiter.kitty.http.message;

/**
 * @author Julian Jupiter
 */
public interface Message {
    ProtocolVersion version();

    HttpHeaders headers();

    HttpBody body();
}
