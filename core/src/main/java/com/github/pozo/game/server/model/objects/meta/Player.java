package com.github.pozo.game.server.model.objects.meta;

import com.github.pozo.game.server.control.event.model.GameModelEvent;
import com.github.pozo.game.server.model.GameModel;
import com.github.pozo.game.server.model.ModelEvent;
import com.github.pozo.game.server.model.ModelEventConsumer;
import com.github.pozo.game.server.model.collate.modelstate.GameModelCollator;
import com.github.pozo.game.server.model.collate.modelstate.GameModelState;
import com.github.pozo.game.server.model.objects.Owner;
import com.github.pozo.game.server.model.objects.artificial.ArtificialObject;
import com.github.pozo.game.server.model.objects.artificial.ship.Ship;
import com.github.pozo.game.server.model.objects.natural.AstronomicalObject;
import com.github.pozo.game.server.model.unit.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Player implements Owner, ModelEventConsumer<ModelEvent> {
    private static final Coordinate INITIAL_COORDINATE = new Coordinate(0, 0);
    private static Player NPC;
    private final PlayerProperties playerProperties;

    private final PlayerId playerId;


    private ModelEventConsumer<ModelEvent> consumerDelegate;

    private List<AstronomicalObject> ownedAstronomicalObjects = new ArrayList<AstronomicalObject>();
    private List<ArtificialObject> ownedArtificialObjects = new ArrayList<ArtificialObject>();

    public Player(PlayerId playerId) {
        this.playerId = playerId;
        this.playerProperties = new PlayerProperties();
        this.playerProperties.setScreenPosition(INITIAL_COORDINATE);
    }

    public static Player getNPC() {
        if (NPC == null) {
            final PlayerId npdID = new PlayerId("NPC");
            NPC = new Player(npdID);
        }
        return NPC;
    }

    public void consume(ModelEvent modelEvent) {
        if (consumerDelegate != null) {
            consumerDelegate.consume(modelEvent);
        } else {
            System.err.println("the is not consumerDelegate");
        }
    }

    public boolean hasConsumerDelegate() {
        return consumerDelegate != null;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public void addArtificialObject(ArtificialObject artificialObject) {
        this.ownedArtificialObjects.add(artificialObject);
        artificialObject.setOwner(this);
    }

    public void removeArtificialObject(ArtificialObject artificialObject) {
        this.ownedArtificialObjects.remove(artificialObject);
        artificialObject.setOwner(Player.getNPC());
    }

    public List<ArtificialObject> getArtificialObjects() {
        return ownedArtificialObjects;
    }

    public Ship getShip(String shipId) {
        for (ArtificialObject artificialObject : ownedArtificialObjects) {
            if (artificialObject.getID().equals(shipId)) {
                return (Ship) artificialObject;
            }
        }
        throw new IllegalArgumentException("There is no such ship");
    }

    public PlayerProperties getPlayerProperties() {
        return playerProperties;
    }

    public void setScreenHeight(int screenHeight) {
        this.playerProperties.setScreenHeight(screenHeight);
    }

    public void setScreenWidth(int screenWidth) {
        this.playerProperties.setScreenWidth(screenWidth);
    }

    public void setScreenPosition(Coordinate screenPosition) {
        this.playerProperties.setScreenPosition(screenPosition);
    }

    public void setConsumerDelegate(ModelEventConsumer<ModelEvent> consumerDelegate) {
        this.consumerDelegate = consumerDelegate;
    }

    public void update(GameModel gameModel) {
        if (hasConsumerDelegate()) {
            final PlayerProperties playerProperties = getPlayerProperties();
            final GameModelCollator gameModelCollator = new GameModelCollator(gameModel, playerProperties);
            final GameModelState gameModelState = gameModelCollator.collateObjects();
            //gameModelState.setPlayerProperties(getPlayerProperties());
            consume(GameModelEvent.createGameUpdateEvent(gameModelState));
        }

    }
}
