package com.github.pozo.game.server.model.objects.natural;

import com.github.pozo.game.server.model.unit.Coordinate;
import com.github.pozo.game.server.model.unit.DistanceUnit;
import com.github.pozo.game.server.model.unit.MassUnit;

public class StarBuilder {
    private DistanceUnit diameter;
    private MassUnit mass;
    private Coordinate coordinate;
    private String id;

    public StarBuilder setDiameter(DistanceUnit diameter) {
        this.diameter = diameter;
        return this;
    }

    public StarBuilder setMass(MassUnit mass) {
        this.mass = mass;
        return this;
    }

    public StarBuilder setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public StarBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public Star createStar() {
        return new Star(diameter, mass, coordinate, id);
    }
}