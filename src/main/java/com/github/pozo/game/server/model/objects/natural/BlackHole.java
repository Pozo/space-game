package com.github.pozo.game.server.model.objects.natural;

import com.github.pozo.game.server.model.unit.Coordinate;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeModelEvent;
import com.github.pozo.game.server.model.unit.DistanceUnit;
import com.github.pozo.game.server.model.unit.MassUnit;

/**
 * Created by pozo on 2016.06.04..
 */
public class BlackHole extends DefaultAstronomicalObject {
    BlackHole(DistanceUnit diameter, MassUnit mass, Coordinate coordinate, String id) {
        super(diameter,mass,coordinate,id);
    }

    public void consume(TimeModelEvent event) {

    }
}
