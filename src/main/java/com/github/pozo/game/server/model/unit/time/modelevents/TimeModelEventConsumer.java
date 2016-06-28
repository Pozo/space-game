package com.github.pozo.game.server.model.unit.time.modelevents;

import com.github.pozo.game.server.model.ModelEventConsumer;

/**
 * Created by pozo on 2016.06.06..
 */
public interface TimeModelEventConsumer extends ModelEventConsumer<TimeModelEvent> {
    void consume(TimeModelEvent event);
}
