package com.github.pozo.game.server.model.objects.natural;

import com.github.pozo.game.server.model.objects.Owner;
import com.github.pozo.game.server.model.unit.Coordinate;
import com.github.pozo.game.server.model.unit.DistanceUnit;
import com.github.pozo.game.server.model.unit.MassUnit;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeModelEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class StarSystem implements AstronomicalObject {
    private final Star star;
    private final HashMap<DistanceUnit, DefaultAstronomicalObject> astronomicalObjects;

    public StarSystem(Star star, HashMap<DistanceUnit, DefaultAstronomicalObject> astronomicalObjects) {
        this.star = star;
        this.astronomicalObjects = astronomicalObjects;
    }

    public DistanceUnit getDiameter() {
        float distance = 0;
        for (Map.Entry<DistanceUnit, DefaultAstronomicalObject> astronomicalObjectEntry : astronomicalObjects.entrySet()) {
            DistanceUnit orbit = astronomicalObjectEntry.getKey();
            distance += orbit.getValue();
        }

        return new DistanceUnit(distance);
    }

    public MassUnit getMass() {
        float mass = 0;
        for (Map.Entry<DistanceUnit, DefaultAstronomicalObject> astronomicalObjectEntry : astronomicalObjects.entrySet()) {
            DefaultAstronomicalObject defaultAstronomicalObject = astronomicalObjectEntry.getValue();
            mass += defaultAstronomicalObject.getMass().getValue();
        }
        return new MassUnit(mass);
    }

    public Coordinate getCoordinate() {
        return star.getCoordinate();
    }

    public Star getStar() {
        return star;
    }

    public DefaultAstronomicalObject getAstronomicalObject(DistanceUnit orbit) {
        return astronomicalObjects.get(orbit);
    }

    public Set<DistanceUnit> getOrbits() {
        return astronomicalObjects.keySet();
    }

    public Collection<DefaultAstronomicalObject> getAstronomicalObjectsFromOrbits() {
        return astronomicalObjects.values();
    }

    @Override
    public String toString() {
        return "StarSystem{" +
                "star=" + star +
                ", astronomicalObjects=" + astronomicalObjects +
                "} " + super.toString();
    }

    public void consume(TimeModelEvent event) {

    }

    public String getID() {
        return null;
    }

    public boolean hasOwner() {
        return false;
    }

    public void removeOwner() {

    }

    public Owner getOwner() {
        return null;
    }

    public void setOwner(Owner owner) {

    }
}
