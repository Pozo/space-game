package com.github.pozo.game.server.model.unit;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by pozo on 2016.06.05..
 */
public class Route {
    LinkedList<Coordinate> coordinates = new LinkedList<Coordinate>();

    public void addDestination(Coordinate coordinate) {
        this.coordinates.add(coordinate);
    }

    public void removeDestination(Coordinate coordinate) {
        this.coordinates.remove(coordinate);
    }

    public List<Coordinate> getDestinations() {
        return coordinates;
    }

    public boolean hasDestination() {
        return !coordinates.isEmpty();
    }

    public Coordinate removeFirstDestination() {
        return coordinates.removeFirst();
    }

    public Coordinate getNextDestination() {
        return coordinates.getFirst();
    }
}
