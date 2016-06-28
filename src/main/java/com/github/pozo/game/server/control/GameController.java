package com.github.pozo.game.server.control;

import com.github.pozo.game.server.GameModel;
import com.github.pozo.game.server.model.objects.meta.Player;

/**
 * Created by pozo on 2016.06.07..
 */
public class GameController {
    private final Player player;
    private final GameModel model;

    public GameController(Player player, GameModel model) {
        this.player = player;
        this.model = model;
    }


    public void dispatchEvent(UserEvent userEvent) {
        new Thread(new UserEventWorker(userEvent), "Player worker thread").run();
    }

    private class UserEventWorker implements Runnable {
        private final UserEvent event;

        public UserEventWorker(UserEvent event) {
            this.event = event;
        }

        public void run() {
            try {
                player.produce(event);
            } catch (Exception exception) {
                exception.printStackTrace();
                System.err.println(exception.getMessage());
            }
        }
    }
}
