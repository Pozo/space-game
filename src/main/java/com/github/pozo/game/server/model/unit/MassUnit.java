package com.github.pozo.game.server.model.unit;

/**
 * Created by pozo on 2016.06.01..
 */
public class MassUnit implements Units {
    private final float value;

    public MassUnit(float value) {
        this.value = value;
    }
    public double getValue() {
        return value;
    }
}
