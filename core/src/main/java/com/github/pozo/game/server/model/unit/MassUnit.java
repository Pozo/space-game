package com.github.pozo.game.server.model.unit;


public class MassUnit implements Units {
    private final float value;

    public MassUnit(float value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
