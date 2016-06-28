package com.github.pozo.game.server;

import com.github.pozo.game.server.model.ModelEventConsumer;
import com.github.pozo.game.server.model.ModelEventProducer;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeModelEvent;

/**
 * Created by pozo on 2016.06.06..
 */
class Clock extends ModelEventProducer<TimeModelEvent> {

    public void produce(TimeModelEvent event) {
        for (ModelEventConsumer modelEventConsumer : getConsumers()) {
            modelEventConsumer.consume(event);
        }
    }
}
