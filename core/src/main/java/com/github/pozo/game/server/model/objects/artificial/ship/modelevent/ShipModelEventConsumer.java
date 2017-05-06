package com.github.pozo.game.server.model.objects.artificial.ship.modelevent;

import com.github.pozo.game.server.model.ModelEventConsumer;


public interface ShipModelEventConsumer extends ModelEventConsumer<ShipModelEvent> {
    void consume(ShipModelEvent event);
}
