package com.github.pozo.game.server.model.objects.artificial.ship.modelevent;

import com.github.pozo.game.server.model.ModelEvent;
import com.github.pozo.game.server.model.objects.artificial.ship.Ship;


public class ShipModelEvent extends ModelEvent<ShipModelEventTypes, Ship> {

    ShipModelEvent(ShipModelEventTypes eventType, Ship eventData) {
        super(eventType, eventData);
    }

    public static ShipModelEvent createEvent(ShipModelEventTypes eventTypes, Ship ship) {
        return new ShipModelEvent(eventTypes, ship);
    }
}
