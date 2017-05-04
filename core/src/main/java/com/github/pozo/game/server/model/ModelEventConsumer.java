package com.github.pozo.game.server.model;

/**
 * Created by pozo on 2016.06.05..
 */
public interface ModelEventConsumer<T extends ModelEvent> {
    void consume(T event);
}
