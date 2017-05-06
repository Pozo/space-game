package com.github.pozo.game.server.control.event;

import com.github.pozo.game.server.control.event.user.UserEvent;
import com.github.pozo.game.server.serialize.EventSerializer;

public class EventParser {
    private final EventSerializer eventSerializer;

    public EventParser(EventSerializer eventSerializer) {
        this.eventSerializer = eventSerializer;
    }

    public UserEvent parse(String finalMessage) {
        return eventSerializer.deserialize(finalMessage);
    }
}
