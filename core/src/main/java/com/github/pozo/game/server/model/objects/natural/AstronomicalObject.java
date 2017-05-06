package com.github.pozo.game.server.model.objects.natural;

import com.github.pozo.game.server.model.objects.Identifier;
import com.github.pozo.game.server.model.objects.Material;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeModelEventConsumer;


public interface AstronomicalObject extends Identifier, Material, TimeModelEventConsumer {
}
