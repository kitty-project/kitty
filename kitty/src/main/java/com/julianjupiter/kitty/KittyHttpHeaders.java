package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Header;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Julian Jupiter
 */
final class KittyHttpHeaders {
    private final Set<Header> headerSet = new HashSet<>();

    private KittyHttpHeaders() {
    }

    public static KittyHttpHeaders create() {
        return new KittyHttpHeaders();
    }

    public KittyHttpHeaders add(String name, String value) {
        this.headerSet.add(new KittyHeader(name, value));

        return this;
    }

    public KittyHttpHeaders add(Header header) {
        this.headerSet.add(header);

        return this;
    }

    public KittyHttpHeaders add(Header[] headers) {
        var collection = Arrays.stream(headers).collect(Collectors.toUnmodifiableSet());
        this.headerSet.addAll(collection);

        return this;
    }

    public Set<Header> get() {
        return this.headerSet;
    }

    public Optional<Header> get(String name) {
        return this.headerSet.stream()
                .filter(header -> header.name().equals(name))
                .findFirst();
    }
}
