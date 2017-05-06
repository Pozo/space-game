package com.github.pozo.game.server.control.event.user;


public interface UserEventConsumer<T extends UserEvent> {
    void consume(T event);
}
