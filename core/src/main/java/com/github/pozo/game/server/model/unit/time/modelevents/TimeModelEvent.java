package com.github.pozo.game.server.model.unit.time.modelevents;

import com.github.pozo.game.server.model.ModelEvent;
import com.github.pozo.game.server.model.unit.time.TimeUnits;

/**
 * Created by pozo on 2016.06.06..
 */
public class TimeModelEvent extends ModelEvent<TimeEventTypes, TimeUnits> {

    public TimeModelEvent(TimeEventTypes eventType, TimeUnits eventData) {
        super(eventType, eventData);
    }
}
