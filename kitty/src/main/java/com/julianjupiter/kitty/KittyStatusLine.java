package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpStatus;
import com.julianjupiter.kitty.http.message.util.HttpVersion;
import com.julianjupiter.kitty.http.message.StatusLine;

import java.util.Objects;

/**
 * @author Julian Jupiter
 */
record KittyStatusLine(HttpStatus status, HttpVersion version) implements StatusLine {
    KittyStatusLine(HttpStatus status) {
        this(status, null);
    }

    KittyStatusLine(HttpStatus status, HttpVersion version) {
        this.status = Objects.requireNonNullElse(status, HttpStatus.OK);
        this.version = Objects.requireNonNullElse(version, HttpVersion.HTTP_1_1);
    }

    @Override
    public String toString() {
        return this.version.toString() + " " + this.status.toString();
    }
}
