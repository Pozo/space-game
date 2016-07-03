package com.github.pozo.game.server.model.objects.natural;

import com.github.pozo.game.server.model.objects.Owner;
import com.github.pozo.game.server.model.unit.Coordinate;
import com.github.pozo.game.server.model.unit.DistanceUnit;
import com.github.pozo.game.server.model.unit.MassUnit;
import com.github.pozo.game.server.model.unit.time.TimeUnits;

import java.util.ArrayList;

public class PlanetBuilder {
    private DistanceUnit diameter;
    private MassUnit mass;
    private ArrayList<DefaultAstronomicalObject> defaultAstronomicalObjects;
    private TimeUnits orbitalPeriod;
    private TimeUnits rotationPeriod;
    private Coordinate coordinate;
    private String id;
    private Owner owner;

    public PlanetBuilder setDiameter(DistanceUnit diameter) {
        this.diameter = diameter;
        return this;
    }

    public PlanetBuilder setMass(MassUnit mass) {
        this.mass = mass;
        return this;
    }

    public PlanetBuilder setDefaultAstronomicalObjects(ArrayList<DefaultAstronomicalObject> defaultAstronomicalObjects) {
        this.defaultAstronomicalObjects = defaultAstronomicalObjects;
        return this;
    }

    public PlanetBuilder setOrbitalPeriod(TimeUnits orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
        return this;
    }

    public PlanetBuilder setRotationPeriod(TimeUnits rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
        return this;
    }

    public PlanetBuilder setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public PlanetBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public PlanetBuilder setOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public Planet createPlanet() {
        return new Planet(owner, diameter, mass, defaultAstronomicalObjects, orbitalPeriod, rotationPeriod, coordinate, id);
    }
}