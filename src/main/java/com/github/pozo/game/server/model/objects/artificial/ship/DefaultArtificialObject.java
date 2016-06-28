package com.github.pozo.game.server.model.objects.artificial.ship;

import com.github.pozo.game.server.model.unit.Coordinate;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeModelEvent;
import com.github.pozo.game.server.model.objects.meta.Player;
import com.github.pozo.game.server.model.objects.Owner;
import com.github.pozo.game.server.model.objects.artificial.ArtificialObject;

/**
 * Created by pozo on 2016.06.07..
 */
public abstract class DefaultArtificialObject implements ArtificialObject {
    private Owner owner;

    private Coordinate coordinate;
    private String id;

    protected DefaultArtificialObject(Owner owner, Coordinate coordinate, String id) {
        this.owner = owner;
        this.coordinate = coordinate;
        this.id = id;
    }
    public String getID() {
        return id;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public abstract void consume(TimeModelEvent event);

    public boolean hasOwner() {
        return owner != null;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void removeOwner() {
        setOwner(Player.getNPC());
    }

    public Owner getOwner() {
        return owner;
    }
}
