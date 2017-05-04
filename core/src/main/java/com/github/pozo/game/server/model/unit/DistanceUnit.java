package com.github.pozo.game.server.model.unit;

/**
 * Created by pozo on 2016.05.25..
 */
public class DistanceUnit implements Units {
    private final float value;

    public DistanceUnit(float value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
