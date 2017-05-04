package com.github.pozo.game.client;

/**
 * Created by pozo on 2016.06.19..
 */
public final class RepositoryKey<T> {
    private final T type;

    public RepositoryKey(T type) {
        this.type = type;
    }

    public T getKey() {
        return type;
    }
}