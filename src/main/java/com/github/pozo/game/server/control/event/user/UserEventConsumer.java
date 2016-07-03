package com.github.pozo.game.server.control.event.user;

/**
 * Created by pozo on 2016.06.05..
 */
public interface UserEventConsumer<T extends UserEvent> {
    void consume(T event);
}