package com.github.pozo.game.server.control.event.model;

import com.github.pozo.game.server.model.ModelEvent;
import com.github.pozo.game.server.model.collate.modelstate.GameModelState;

public class GameModelEvent extends ModelEvent<GameModelEventType, GameModelState> {
    public GameModelEvent(GameModelEventType eventType, GameModelState eventData) {
        super(eventType, eventData);
    }

    public static GameModelEvent createGameUpdateEvent(GameModelState gameModelState) {
        return new GameModelEvent(GameModelEventType.UPDATE, gameModelState);
    }
}
