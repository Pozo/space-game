package com.github.pozo.game.server.model.unit.time;

import com.github.pozo.game.server.model.unit.Units;


public class TimeUnits implements Units {
    private final float value;

    public TimeUnits(float value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
