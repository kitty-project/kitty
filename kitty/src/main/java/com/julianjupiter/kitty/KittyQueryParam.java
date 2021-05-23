package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.QueryParam;

/**
 * @author Julian Jupiter
 */
record KittyQueryParam(String key, String[] values) implements QueryParam {
    KittyQueryParam(String key, String value) {
        this(key, new String[]{value});
    }
}
