package com.julianjupiter.kitty;

public class Pair<T, U> {
    private final T t;
    private final U u;

    private Pair (T t, U u) {
        this.t = t;
        this.u = u;
    }

    public static <T, U> Pair<T, U> of (T t, U u) {
        return new Pair<>(t, u);
    }

    public T first() {
        return t;
    }

    public U second() {
        return u;
    }
}
