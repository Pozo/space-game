package com.github.pozo.game.server.model.unit.time;

import com.github.pozo.game.server.model.unit.Units;

/**
 * Created by pozo on 2016.06.01..
 */
public class TimeUnits implements Units {
    private final float value;

    public TimeUnits(float value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
