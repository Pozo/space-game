package com.github.pozo.game.server.model.objects.artificial.ship.userevent;

import com.github.pozo.game.server.control.event.user.UserEvent;
import com.github.pozo.game.server.model.objects.artificial.ship.Ship;
import com.github.pozo.game.server.model.unit.Coordinate;


public class ShipUserEvent extends UserEvent<ShipUserEventTypes, Ship, Coordinate> {

    ShipUserEvent(ShipUserEventTypes eventType, Ship eventSubject, Coordinate eventData) {
        super(eventType, eventSubject, eventData);
    }

    public static ShipUserEvent createShipUserEvent(ShipUserEventTypes eventTypes, Ship ship, Coordinate newCoordinate) {
        return new ShipUserEvent(eventTypes, ship, newCoordinate);
    }

}
