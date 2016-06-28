package com.github.pozo.game.server.model.objects.artificial.ship.modelevent;

import com.github.pozo.game.server.model.ModelEventConsumer;
import com.github.pozo.game.server.model.ModelEventProducer;

/**
 * Created by pozo on 2016.06.06..
 */
public class ShipModelEventProducer extends ModelEventProducer<ShipModelEvent> {
    @Override
    public void produce(ShipModelEvent event) {
        for (ModelEventConsumer<ShipModelEvent> modelEventConsumer : getConsumers()) {
            modelEventConsumer.consume(event);
        }
    }
}
