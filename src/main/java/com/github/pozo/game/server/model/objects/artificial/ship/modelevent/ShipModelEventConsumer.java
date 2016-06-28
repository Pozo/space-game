package com.github.pozo.game.server.model.objects.artificial.ship.modelevent;

import com.github.pozo.game.server.model.ModelEventConsumer;

/**
 * Created by pozo on 2016.06.06..
 */
public interface ShipModelEventConsumer extends ModelEventConsumer<ShipModelEvent> {
    void consume(ShipModelEvent event);
}
