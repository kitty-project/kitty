package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.QueryParam;

/**
 * @author Julian Jupiter
 */
record DefaultQueryParam(String key, String[] values) implements QueryParam {
    DefaultQueryParam(String key, String value) {
        this(key, new String[]{value});
    }
}
