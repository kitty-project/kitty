package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpStatus;
import com.julianjupiter.kitty.http.message.util.HttpVersion;

/**
 * @author Julian Jupiter
 */
interface StatusLine {
    HttpVersion version();

    HttpStatus status();
}
