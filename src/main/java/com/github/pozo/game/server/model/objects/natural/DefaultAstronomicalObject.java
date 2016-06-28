package com.github.pozo.game.server.model.objects.natural;

import com.github.pozo.game.server.model.unit.Coordinate;
import com.github.pozo.game.server.model.unit.DistanceUnit;
import com.github.pozo.game.server.model.unit.MassUnit;

/**
 * Created by pozo on 2016.05.25..
 */
public abstract class DefaultAstronomicalObject implements AstronomicalObject {
    private DistanceUnit diameter;
    private MassUnit mass;
    private Coordinate coordinate;
    private String id;

    protected DefaultAstronomicalObject(DistanceUnit diameter, MassUnit mass, Coordinate coordinate, String id) {
        this.diameter = diameter;
        this.mass = mass;
        this.coordinate = coordinate;
        this.id = id;
    }

    public DistanceUnit getDiameter() {
        return diameter;
    }

    public MassUnit getMass() {
        return mass;
    }

    public String getID() {
        return id;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
