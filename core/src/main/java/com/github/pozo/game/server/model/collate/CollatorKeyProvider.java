package com.github.pozo.game.server.model.collate;

/**
 * Created by pozo on 2016.07.02..
 */
public interface CollatorKeyProvider<T> {
    String getKey(T subject);
}
