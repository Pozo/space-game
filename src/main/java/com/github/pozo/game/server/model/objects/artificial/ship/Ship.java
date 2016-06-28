package com.github.pozo.game.server.model.objects.artificial.ship;

import com.github.pozo.game.server.control.GameUserEventType;
import com.github.pozo.game.server.control.UserEvent;
import com.github.pozo.game.server.model.objects.Owner;
import com.github.pozo.game.server.model.objects.artificial.ship.modelevent.ShipModelEvent;
import com.github.pozo.game.server.model.objects.artificial.ship.modelevent.ShipModelEventProducer;
import com.github.pozo.game.server.model.objects.artificial.ship.modelevent.ShipModelEventTypes;
import com.github.pozo.game.server.model.objects.artificial.ship.userevent.ShipUserEventTypes;
import com.github.pozo.game.server.model.unit.Coordinate;
import com.github.pozo.game.server.model.unit.DistanceUnit;
import com.github.pozo.game.server.model.unit.Route;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeModelEvent;
import com.github.pozo.game.server.model.unit.time.TimeUnits;

import java.util.List;


public class Ship extends DefaultArtificialObject {
    private final Route route;
    private final ShipModelEventProducer shipEventProducer;
    private DistanceUnit currentSpeed = new DistanceUnit(1);
    private Coordinate reachedTarget;

    Ship(Owner owner, Coordinate coordinate, String id, ShipModelEventProducer shipEventProducer, Route route) {
        super(owner, coordinate,id);

        this.shipEventProducer = shipEventProducer;
        this.route = route;
    }
    void move(TimeUnits time, Coordinate destination) {
        Coordinate currentPosition = getCoordinate();
        final double nextStep = getNextStep(currentPosition, destination);

        if(isRemainingSmallerThanOneStep(currentPosition, destination, nextStep)) {
            currentPosition = moveCloserTo(currentPosition, destination, nextStep);
        } else {
            currentPosition = destination;
            reachedTarget = route.removeFirstDestination();

            produceEnclosingEvents();
        }
        setCoordinate(currentPosition);

    }
    private Coordinate moveCloserTo(Coordinate currentPosition, Coordinate destination,double nextStep) {
        final Coordinate nextCoordinate = destination.sub(currentPosition).normalize().multiply(nextStep);
        currentPosition = currentPosition.add(nextCoordinate);

        shipEventProducer.produce(ShipModelEvent.createEvent(ShipModelEventTypes.LOCATION_CHANGED, this));
        if(currentPosition.equals(destination)) {
            reachedTarget = route.removeFirstDestination();

            produceEnclosingEvents();
        }
        return currentPosition;
    }
    public void produceCurrentCoordinate() {
        shipEventProducer.produce(ShipModelEvent.createEvent(ShipModelEventTypes.LOCATION_CHANGED, this));
    }
    private void produceEnclosingEvents() {
        shipEventProducer.produce(ShipModelEvent.createEvent(ShipModelEventTypes.TARGET_REACHED, this));
        if(!route.hasDestination()) {
            shipEventProducer.produce(ShipModelEvent.createEvent(ShipModelEventTypes.STOPPED, this));
        }
    }
    private double getNextStep(Coordinate currentPosition, Coordinate destination) {
        double plannedDistance = currentSpeed.getValue();
        double fullDistance = currentPosition.getDistance(destination);
        return Math.min(fullDistance, plannedDistance);
    }

    private boolean isRemainingSmallerThanOneStep(Coordinate currentPosition, Coordinate currentTarget, double nextStep) {
        return currentPosition.getDistance(currentTarget) > nextStep;
    }
    public DistanceUnit getCurrentSpeed() {
        return currentSpeed;
    }
    public List<Coordinate> getDestinations() {
        return route.getDestinations();
    }
    public void setCurrentSpeed(DistanceUnit currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void addDestination(Coordinate coordinate) {
        route.addDestination(coordinate);
    }
    public void consume(TimeModelEvent event) {
        TimeUnits timeSpent = event.getData();
        if (route.hasDestination()) {
            move(timeSpent, route.getNextDestination());
        }
    }
    public void consume(UserEvent event) {
        if (ShipUserEventTypes.MOVE.equals(event.getType())) {
            Coordinate newCoordinate = (Coordinate) event.getData();
            addDestination(newCoordinate);
        } else if (GameUserEventType.LOGIN.equals(event.getType())) {
            produceCurrentCoordinate();
        }
    }

    @Override
    public String toString() {
        return "Ship{" +
                "route=" + route +
                ", shipEventProducer=" + shipEventProducer +
                ", currentSpeed=" + currentSpeed +
                "} " + super.toString();
    }

    public Coordinate getReachedTarget() {
        return reachedTarget;
    }
}
