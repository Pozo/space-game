package com.github.pozo.game.server.model.unit;


public class DistanceUnit implements Units {
    private final float value;

    public DistanceUnit(float value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
