package com.github.pozo.game.server.model.collate.modelstate;

import com.github.pozo.game.server.model.objects.artificial.ship.Ship;
import com.github.pozo.game.server.model.objects.natural.BlackHole;
import com.github.pozo.game.server.model.objects.natural.Comet;
import com.github.pozo.game.server.model.objects.natural.Galaxy;
import com.github.pozo.game.server.model.objects.natural.Planet;
import com.github.pozo.game.server.model.objects.natural.Star;
import com.github.pozo.game.server.model.objects.natural.StarSystem;


public class GenericKeyFactory {
    String getTypeID(Class clazz) {
        if (BlackHole.class.equals(clazz)) {
            return "BH";
        } else if (Comet.class.equals(clazz)) {
            return "C";
        } else if (Galaxy.class.equals(clazz)) {
            return "GL";
        } else if (Planet.class.equals(clazz)) {
            return "PL";
        } else if (Ship.class.equals(clazz)) {
            return "S";
        } else if (Star.class.equals(clazz)) {
            return "ST";
        } else if (StarSystem.class.equals(clazz)) {
            return "SS";
        } else {
            throw new IllegalArgumentException("There is no TypeID for " + clazz);
        }
    }
}
