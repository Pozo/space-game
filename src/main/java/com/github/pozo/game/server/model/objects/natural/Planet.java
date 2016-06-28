package com.github.pozo.game.server.model.objects.natural;

import com.github.pozo.game.server.model.objects.Ownable;
import com.github.pozo.game.server.model.objects.Owner;
import com.github.pozo.game.server.model.objects.meta.Player;
import com.github.pozo.game.server.model.unit.Coordinate;
import com.github.pozo.game.server.model.unit.DistanceUnit;
import com.github.pozo.game.server.model.unit.MassUnit;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeModelEvent;
import com.github.pozo.game.server.model.unit.time.TimeUnits;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pozo on 2016.05.25..
 */
public class Planet extends DefaultAstronomicalObject implements Ownable {
    private Owner owner;
    private ArrayList<DefaultAstronomicalObject> defaultAstronomicalObjects;

    private TimeUnits orbitalPeriod;
    private TimeUnits rotationPeriod;

    Planet(Owner owner, DistanceUnit diameter, MassUnit mass, ArrayList<DefaultAstronomicalObject> defaultAstronomicalObjects, TimeUnits orbitalPeriod, TimeUnits rotationPeriod, Coordinate coordinate, String id) {
        this(diameter,mass,coordinate,id);

        this.owner = owner;
        this.defaultAstronomicalObjects = defaultAstronomicalObjects;
        this.orbitalPeriod = orbitalPeriod;
        this.rotationPeriod = rotationPeriod;
    }

    Planet(DistanceUnit diameter, MassUnit mass, Coordinate coordinate, String id) {
        super(diameter, mass, coordinate, id);
    }

    public List<DefaultAstronomicalObject> getSurroundingAstronomicalObjects() {
        defaultAstronomicalObjects = new ArrayList<DefaultAstronomicalObject>();
        return defaultAstronomicalObjects;
    }

    public TimeUnits getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public TimeUnits getRotationPeriod() {
        return rotationPeriod;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "defaultAstronomicalObjects=" + defaultAstronomicalObjects +
                ", orbitalPeriod=" + orbitalPeriod +
                ", rotationPeriod=" + rotationPeriod +
                "} " + super.toString();
    }

    public void consume(TimeModelEvent event) {

    }

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
