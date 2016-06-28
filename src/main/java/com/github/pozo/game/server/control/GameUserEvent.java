package com.github.pozo.game.server.control;

import com.github.pozo.game.server.model.objects.meta.Player;
import com.github.pozo.game.server.model.objects.meta.PlayerSettings;

/**
 * Created by pozo on 2016.06.16..
 */
public class GameUserEvent extends UserEvent<GameUserEventType, Player, PlayerSettings> {
    GameUserEvent(GameUserEventType eventType, Player eventSubject, PlayerSettings eventData) {
        super(eventType, eventSubject, eventData);
    }

    public static UserEvent createLoginEvent(Player player,PlayerSettings playerSettings) {
        return new UserEvent<GameUserEventType,Player, PlayerSettings>(GameUserEventType.LOGIN, player, playerSettings);
    }
}
