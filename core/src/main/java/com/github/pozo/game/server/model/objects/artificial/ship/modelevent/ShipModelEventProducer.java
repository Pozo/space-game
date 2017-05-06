package com.github.pozo.game.server.model.objects.artificial.ship.modelevent;

import com.github.pozo.game.server.model.ModelEventConsumer;
import com.github.pozo.game.server.model.ModelEventProducer;


public class ShipModelEventProducer extends ModelEventProducer<ShipModelEvent> {
    @Override
    public void produce(ShipModelEvent event) {
        for (ModelEventConsumer<ShipModelEvent> modelEventConsumer : getConsumers()) {
            modelEventConsumer.consume(event);
        }
    }
}
