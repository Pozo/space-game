package com.github.pozo.game.server.control.event.user;

import com.github.pozo.game.server.model.objects.meta.Player;
import com.github.pozo.game.server.model.objects.meta.PlayerProperties;


public class GameUserEvent extends UserEvent<GameUserEventType, Player, PlayerProperties> {
    GameUserEvent(GameUserEventType eventType, Player eventSubject, PlayerProperties eventData) {
        super(eventType, eventSubject, eventData);
    }

    public static UserEvent createLoginEvent(Player player, PlayerProperties playerProperties) {
        return new UserEvent<GameUserEventType, Player, PlayerProperties>(GameUserEventType.LOGIN, player, playerProperties);
    }

    public static UserEvent createChangePreferencesEvent(Player player, PlayerProperties playerProperties) {
        return new UserEvent<GameUserEventType, Player, PlayerProperties>(GameUserEventType.CHANGE_PREFERENCES, player, playerProperties);
    }
}
