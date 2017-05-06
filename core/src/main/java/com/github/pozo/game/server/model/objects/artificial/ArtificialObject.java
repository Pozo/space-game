package com.github.pozo.game.server.model.objects.artificial;

import com.github.pozo.game.server.model.objects.Identifier;
import com.github.pozo.game.server.model.objects.Ownable;
import com.github.pozo.game.server.model.objects.Spatial;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeModelEventConsumer;


public interface ArtificialObject extends Identifier, Spatial, TimeModelEventConsumer, Ownable {
}
