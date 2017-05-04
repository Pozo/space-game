package com.github.pozo.game.server.control;

import com.github.pozo.game.server.control.event.user.GameUserEventType;
import com.github.pozo.game.server.control.event.user.UserEvent;
import com.github.pozo.game.server.control.event.user.UserEventConsumer;
import com.github.pozo.game.server.model.GameModel;
import com.github.pozo.game.server.model.objects.artificial.ship.Ship;
import com.github.pozo.game.server.model.objects.artificial.ship.userevent.ShipUserEventTypes;
import com.github.pozo.game.server.model.objects.meta.Player;
import com.github.pozo.game.server.model.objects.meta.PlayerProperties;
import com.github.pozo.game.server.model.unit.Coordinate;

/**
 * Created by pozo on 2016.06.07..
 */
public class GameController {
    private final Player player;
    private final GameModel gameModel;

    public GameController(Player player, GameModel gameModel) {
        this.player = player;
        this.gameModel = gameModel;
    }


    public void dispatchEvent(UserEvent userEvent) {
        new Thread(new UserEventConsumerWorker(userEvent), "Player worker thread").run();
    }

    private class UserEventConsumerWorker implements Runnable, UserEventConsumer {
        private final UserEvent event;

        public UserEventConsumerWorker(UserEvent event) {
            this.event = event;
        }

        public void run() {
            try {
                consume(event);
            } catch (Exception exception) {
                exception.printStackTrace();
                System.err.println(exception.getMessage());
            }
        }

        public void consume(UserEvent event) {
            if (event.getType().equals(GameUserEventType.CHANGE_PREFERENCES)) {
                PlayerProperties playerProperties = (PlayerProperties) event.getData();

                player.setScreenPosition(playerProperties.getScreenPosition());
                player.setScreenHeight(playerProperties.getScreenHeight());
                player.setScreenWidth(playerProperties.getScreenWidth());
                //System.out.println("event = " + event);
            } else if (event.getType().equals(GameUserEventType.LOGIN)) {
                PlayerProperties playerProperties = (PlayerProperties) event.getData();

                player.setScreenPosition(playerProperties.getScreenPosition());
                player.setScreenHeight(playerProperties.getScreenHeight());
                player.setScreenWidth(playerProperties.getScreenWidth());
                System.out.println("event = " + event);

                player.update(gameModel);
            } else if (event.getType().equals(ShipUserEventTypes.MOVE)) {
                Coordinate newCoordinate = (Coordinate) event.getData();
                Ship ship = (Ship) event.getEventSubject();
                ;

                player.getShip(ship.getID()).addDestination(newCoordinate);

                //player.consume(GameModelEvent.createGameUpdateEvent(modelstate.collateObjects(player.getPlayerProperties())));
            }
        }

    }
}
