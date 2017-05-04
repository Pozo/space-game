package com.github.pozo.game.server.model;

import com.github.pozo.game.server.model.objects.artificial.ArtificialObject;
import com.github.pozo.game.server.model.objects.artificial.ship.Ship;
import com.github.pozo.game.server.model.objects.meta.Player;
import com.github.pozo.game.server.model.objects.meta.PlayerId;
import com.github.pozo.game.server.model.objects.natural.AstronomicalObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pozo on 2016.06.06..
 */
public class GameModel {
    private final Map<Class<? extends AstronomicalObject>, List<AstronomicalObject>> astronomicalObjects = new HashMap<Class<? extends AstronomicalObject>, List<AstronomicalObject>>();
    private final Map<Class<? extends ArtificialObject>, List<ArtificialObject>> artificialObjects = new HashMap<Class<? extends ArtificialObject>, List<ArtificialObject>>();

    private Map<PlayerId, Player> players = new HashMap<PlayerId, Player>();

    public List<ModelEventConsumer> getModelEventConsumers() {
        final ArrayList<ModelEventConsumer> consumers = new ArrayList<ModelEventConsumer>();

        for (List<AstronomicalObject> list : astronomicalObjects.values()) {
            consumers.addAll(list);
        }
        for (List<ArtificialObject> list : artificialObjects.values()) {
            consumers.addAll(list);
        }

        return consumers;
    }

    public void addPlayer(Player player) {
        players.put(player.getPlayerId(), player);
    }

    public Player getPlayer(PlayerId playerId) {
        return players.get(playerId);
    }

    public List<Player> getPlayers() {
        return new ArrayList<Player>(players.values());
    }

    void addArtificialObject(Player player, Ship ship) {
        player.addArtificialObject(ship);
        addArtificialObject(ship);
    }

    public void addAstronomicalObject(AstronomicalObject astronomicalObject) {
        if (astronomicalObjects.containsKey(astronomicalObject.getClass())) {
            astronomicalObjects.get(astronomicalObject.getClass()).add(astronomicalObject);
        } else {
            ArrayList<AstronomicalObject> objects = new ArrayList<AstronomicalObject>();
            objects.add(astronomicalObject);
            astronomicalObjects.put(astronomicalObject.getClass(), objects);
        }
    }

    public void addArtificialObject(ArtificialObject artificialObject) {
        if (astronomicalObjects.containsKey(artificialObject.getClass())) {
            artificialObjects.get(artificialObject.getClass()).add(artificialObject);
        } else {
            ArrayList<ArtificialObject> objects = new ArrayList<ArtificialObject>();
            objects.add(artificialObject);
            artificialObjects.put(artificialObject.getClass(), objects);
        }
    }

    public Map<Class<? extends AstronomicalObject>, List<AstronomicalObject>> getAstronomicalObjects() {
        return astronomicalObjects;
    }

    public Map<Class<? extends ArtificialObject>, List<ArtificialObject>> getArtificialObjects() {
        return artificialObjects;
    }

}
