package com.github.pozo.game.server.model.objects.meta;

import com.github.pozo.game.server.control.UserEvent;
import com.github.pozo.game.server.control.UserEventProducer;
import com.github.pozo.game.server.model.ModelEventConsumer;
import com.github.pozo.game.server.model.ModelEvent;
import com.github.pozo.game.server.model.objects.artificial.ArtificialObject;
import com.github.pozo.game.server.model.objects.artificial.ship.Ship;
import com.github.pozo.game.server.model.objects.natural.AstronomicalObject;
import com.github.pozo.game.server.model.objects.Owner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pozo on 2016.06.06..
 */
public class Player implements Owner, ModelEventConsumer<ModelEvent> {
    private static Player NPC;

    private final PlayerSettings playerSettings;

    private final PlayerId playerId;
    private final UserEventProducer<UserEvent> userEventProducer;

    private ModelEventConsumer<ModelEvent> consumerDelegate;

    private List<AstronomicalObject> ownedAstronomicalObjects = new ArrayList<AstronomicalObject>();
    private List<ArtificialObject> ownedArtificialObjects = new ArrayList<ArtificialObject>();

    public Player(PlayerId playerId) {
        this.playerId = playerId;
        this.userEventProducer = new UserEventProducer<UserEvent>();
        this.playerSettings = new PlayerSettings();
    }
    public void consume(ModelEvent modelEvent) {
        if(consumerDelegate != null) {
            consumerDelegate.consume(modelEvent);
        } else {
            System.err.println("the is not consumerDelegate");
        }
    }

    public PlayerId getPlayerId() {
        return playerId;
    }
    public static Player getNPC() {
        if(NPC == null) {
            final PlayerId npdID = new PlayerId("NPC");
            NPC = new Player(npdID);
        }
        return NPC;
    }
    public void addArtificialObject(ArtificialObject artificialObject) {
        this.ownedArtificialObjects.add(artificialObject);
        this.addConsumer(artificialObject);
        artificialObject.setOwner(this);
    }
    private void addConsumer(ArtificialObject artificialObject) {
        userEventProducer.addConsumer(artificialObject);
    }
    public void produce(UserEvent event) {
        userEventProducer.produce(event);
    }
    public void removeArtificialObject(ArtificialObject artificialObject) {
        this.ownedArtificialObjects.remove(artificialObject);
        artificialObject.setOwner(Player.getNPC());
    }
    public Ship getShip(String shipId) {
        for (ArtificialObject artificialObject : ownedArtificialObjects) {
            if(artificialObject.getID().equals(shipId)) {
                return (Ship) artificialObject;
            }
        }
        throw new IllegalArgumentException("There is no such ship");
    }

    public void setConsumerDelegate(ModelEventConsumer<ModelEvent> consumerDelegate) {
        this.consumerDelegate = consumerDelegate;
    }

    public PlayerSettings getPlayerSettings() {
        return playerSettings;
    }
}
