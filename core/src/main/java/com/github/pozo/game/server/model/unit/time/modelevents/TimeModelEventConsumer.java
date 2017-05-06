package com.github.pozo.game.server.model.unit.time.modelevents;

import com.github.pozo.game.server.model.ModelEventConsumer;


public interface TimeModelEventConsumer extends ModelEventConsumer<TimeModelEvent> {
    void consume(TimeModelEvent event);
}
