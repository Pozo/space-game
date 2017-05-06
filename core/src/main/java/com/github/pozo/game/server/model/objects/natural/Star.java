package com.github.pozo.game.server.model.objects.natural;

import com.github.pozo.game.server.model.unit.Coordinate;
import com.github.pozo.game.server.model.unit.DistanceUnit;
import com.github.pozo.game.server.model.unit.MassUnit;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeModelEvent;


public class Star extends DefaultAstronomicalObject {
    Star(DistanceUnit diameter, MassUnit mass, Coordinate coordinate, String id) {
        super(diameter, mass, coordinate, id);
    }

    public void consume(TimeModelEvent event) {

    }
}
