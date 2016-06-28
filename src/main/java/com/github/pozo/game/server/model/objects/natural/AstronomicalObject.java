package com.github.pozo.game.server.model.objects.natural;

import com.github.pozo.game.server.model.objects.Identifier;
import com.github.pozo.game.server.model.objects.Material;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeModelEventConsumer;

/**
 * Created by pozo on 2016.06.06..
 */
public interface AstronomicalObject extends Identifier, Material, TimeModelEventConsumer {
}
