package com.github.pozo.game.server.serialize;

import com.github.pozo.game.server.control.event.user.UserEvent;
import com.github.pozo.game.server.model.ModelEvent;


public interface EventSerializer {
    String serialize(ModelEvent modelEvent);

    UserEvent deserialize(String finalMessage);
}
