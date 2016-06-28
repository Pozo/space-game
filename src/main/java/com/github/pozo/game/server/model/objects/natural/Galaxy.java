package com.github.pozo.game.server.model.objects.natural;

import com.github.pozo.game.server.model.unit.Coordinate;
import com.github.pozo.game.server.model.unit.DistanceUnit;
import com.github.pozo.game.server.model.unit.MassUnit;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeModelEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pozo on 2016.05.25..
 */
public class Galaxy extends DefaultAstronomicalObject {
    private DefaultAstronomicalObject centralDefaultAstronomicalObjects;
    private ArrayList<DefaultAstronomicalObject> defaultAstronomicalObjects;

    Galaxy(DistanceUnit diameter, MassUnit mass, DefaultAstronomicalObject centralDefaultAstronomicalObjects, ArrayList<DefaultAstronomicalObject> defaultAstronomicalObjects, Coordinate coordinate, String id) {
        super(diameter, mass, coordinate, id);
        this.centralDefaultAstronomicalObjects = centralDefaultAstronomicalObjects;
        this.defaultAstronomicalObjects = defaultAstronomicalObjects;
    }
    public DefaultAstronomicalObject getCentralAstronomicalObject() {
        return centralDefaultAstronomicalObjects;
    }

    public List<DefaultAstronomicalObject> getDefaultAstronomicalObjects() {
        return defaultAstronomicalObjects;
    }

    public void consume(TimeModelEvent event) {

    }
}
