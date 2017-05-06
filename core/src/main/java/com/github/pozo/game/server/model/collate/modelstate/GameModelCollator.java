package com.github.pozo.game.server.model.collate.modelstate;

import com.github.pozo.game.server.model.GameModel;
import com.github.pozo.game.server.model.collate.Collator;
import com.github.pozo.game.server.model.collate.CollatorKeyProvider;
import com.github.pozo.game.server.model.objects.artificial.ArtificialObject;
import com.github.pozo.game.server.model.objects.meta.PlayerProperties;
import com.github.pozo.game.server.model.objects.natural.AstronomicalObject;
import com.github.pozo.game.server.model.unit.Coordinate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameModelCollator {
    private final GameModel gameModel;
    private final PlayerProperties playerProperties;

    public GameModelCollator(GameModel gameModel, PlayerProperties playerProperties) {
        this.gameModel = gameModel;
        this.playerProperties = playerProperties;
    }

    Map<String, List<AstronomicalObject>> collateAstronomicalObjects() {
        Map<String, List<AstronomicalObject>> astronomicalObjects = new HashMap<String, List<AstronomicalObject>>();

        final CollatorKeyProvider<AstronomicalObject> keyProvider = new AstronomicalObjectKeyProvider();
        final Collator<AstronomicalObject> collator = new Collator<AstronomicalObject>(keyProvider);

        for (List<AstronomicalObject> objectList : gameModel.getAstronomicalObjects().values()) {
            for (AstronomicalObject astronomicalObject : objectList) {
                Coordinate coordinate = astronomicalObject.getCoordinate();

                // TODO should rework the authentication and the user property settings
                //if (isOnPlayerScreen(playerProperties, coordinate)) {
                    astronomicalObjects = collator.collate(astronomicalObject);
                //}
            }
        }
        return astronomicalObjects;
    }

    Map<String, List<ArtificialObject>> collateArtificialObjects() {
        Map<String, List<ArtificialObject>> artificialObjects = new HashMap<String, List<ArtificialObject>>();

        final CollatorKeyProvider<ArtificialObject> keyProvider = new ArtificialObjectKeyProvider();
        final Collator<ArtificialObject> collator = new Collator<ArtificialObject>(keyProvider);

        for (List<ArtificialObject> objectList : gameModel.getArtificialObjects().values()) {
            for (ArtificialObject artificialObject : objectList) {
                Coordinate coordinate = artificialObject.getCoordinate();

                if (isOnPlayerScreen(playerProperties, coordinate)) {
                    artificialObjects = collator.collate(artificialObject);
                }
            }
        }
        return artificialObjects;
    }

    private boolean isOnPlayerScreen(PlayerProperties playerProperties, Coordinate coordinate) {
        final Coordinate screenPosition = playerProperties.getScreenPosition();

        final double left = screenPosition.getX();
        final double right = screenPosition.getX() + playerProperties.getScreenWidth();
        final double top = screenPosition.getY();
        final double bottom = screenPosition.getY() - playerProperties.getScreenHeight();

        return coordinate.getX() >= left && coordinate.getX() < right &&
                coordinate.getY() <= top && coordinate.getY() >= bottom;
    }

    public GameModelState collateObjects() {
        final GameModelState gameModelState = new GameModelState();

        gameModelState.setAstronomicalObjects(collateAstronomicalObjects());
        gameModelState.setArtificialObjects(collateArtificialObjects());

        return gameModelState;
    }
}
