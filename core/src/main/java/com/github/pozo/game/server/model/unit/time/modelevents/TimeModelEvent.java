package com.github.pozo.game.server.model.unit.time.modelevents;

import com.github.pozo.game.server.model.ModelEvent;
import com.github.pozo.game.server.model.unit.time.TimeUnits;


public class TimeModelEvent extends ModelEvent<TimeEventTypes, TimeUnits> {

    public TimeModelEvent(TimeEventTypes eventType, TimeUnits eventData) {
        super(eventType, eventData);
    }
}
