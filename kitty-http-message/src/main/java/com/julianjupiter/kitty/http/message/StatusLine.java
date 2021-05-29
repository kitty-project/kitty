package com.julianjupiter.kitty.http.message;

import com.julianjupiter.kitty.http.message.util.HttpStatus;
import com.julianjupiter.kitty.http.message.util.HttpVersion;

/**
 * @author Julian Jupiter
 */
public interface StatusLine {
    HttpVersion version();

    HttpStatus status();
}
