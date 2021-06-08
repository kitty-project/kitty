package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.QueryParam;

/**
 * @author Julian Jupiter
 */
record KittyQueryParam(String name, String[] values) implements QueryParam {
    KittyQueryParam(String name, String value) {
        this(name, new String[]{value});
    }
}
