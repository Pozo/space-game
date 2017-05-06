package com.github.pozo.game.server.model.objects;

import com.github.pozo.game.server.model.unit.DistanceUnit;
import com.github.pozo.game.server.model.unit.MassUnit;


public interface Material extends Spatial {
    DistanceUnit getDiameter();

    MassUnit getMass();
}
