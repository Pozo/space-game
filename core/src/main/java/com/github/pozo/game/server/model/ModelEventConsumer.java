package com.github.pozo.game.server.model;


public interface ModelEventConsumer<T extends ModelEvent> {
    void consume(T event);
}
