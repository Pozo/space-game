package com.github.pozo.game.server.model.collate;


public interface CollatorKeyProvider<T> {
    String getKey(T subject);
}
