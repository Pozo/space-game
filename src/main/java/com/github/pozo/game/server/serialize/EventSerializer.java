package com.github.pozo.game.server.serialize;

import com.github.pozo.game.server.control.UserEvent;
import com.github.pozo.game.server.model.ModelEvent;

/**
 * Created by pozo on 2016.06.14..
 */
public interface EventSerializer {
    String serialize(ModelEvent modelEvent);
    UserEvent deserialize(String finalMessage);
}
