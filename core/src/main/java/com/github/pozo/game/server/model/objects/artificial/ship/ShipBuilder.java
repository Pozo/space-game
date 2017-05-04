package com.github.pozo.game.server.model.objects.artificial.ship;

import com.github.pozo.game.server.model.objects.Owner;
import com.github.pozo.game.server.model.objects.artificial.ship.modelevent.ShipModelEventProducer;
import com.github.pozo.game.server.model.unit.Coordinate;
import com.github.pozo.game.server.model.unit.Route;

public class ShipBuilder {
    private Coordinate coordinate;
    private String id;
    private ShipModelEventProducer shipEventProducer;
    private Route route;
    private Owner owner;

    public ShipBuilder setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public ShipBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public ShipBuilder setShipEventProducer(ShipModelEventProducer shipEventProducer) {
        this.shipEventProducer = shipEventProducer;
        return this;
    }

    public ShipBuilder setRoute(Route route) {
        this.route = route;
        return this;
    }

    public Ship createShip() {
        return new Ship(owner, coordinate, id, shipEventProducer, route);
    }

    public ShipBuilder setOwner(Owner owner) {
        this.owner = owner;
        return this;
    }
}