package com.julianjupiter.kitty.http;

import com.julianjupiter.kitty.http.util.HttpStatus;
import com.julianjupiter.kitty.http.util.HttpVersion;

/**
 * @author Julian Jupiter
 */
public interface StatusLine {

    HttpVersion version();

    HttpStatus status();

}
