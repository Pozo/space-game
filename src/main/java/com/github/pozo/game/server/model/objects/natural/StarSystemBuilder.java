package com.github.pozo.game.server.model.objects.natural;

import com.github.pozo.game.server.model.unit.DistanceUnit;

import java.util.HashMap;

public class StarSystemBuilder {
    private Star star;
    private HashMap<DistanceUnit, DefaultAstronomicalObject> astronomicalObjects = new HashMap<DistanceUnit, DefaultAstronomicalObject>();

    public StarSystemBuilder setStar(Star star) {
        this.star = star;
        return this;
    }
    public StarSystemBuilder withAstronomicalObject(DistanceUnit orbit, DefaultAstronomicalObject defaultAstronomicalObject) {
        this.astronomicalObjects.put(orbit, defaultAstronomicalObject);
        return this;
    }

    public StarSystem createStarSystem() {
        return new StarSystem(star, astronomicalObjects);
    }
}