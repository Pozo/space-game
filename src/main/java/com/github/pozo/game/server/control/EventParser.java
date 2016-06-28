package com.github.pozo.game.server.control;

import com.github.pozo.game.server.serialize.EventSerializer;

/**
 * Created by pozo on 2016.06.13..
 */
public class EventParser {
    private final EventSerializer eventSerializer;

    public EventParser(EventSerializer eventSerializer) {
        this.eventSerializer = eventSerializer;
    }
    public UserEvent parse(String finalMessage) {
        return eventSerializer.deserialize(finalMessage);
    }
}
