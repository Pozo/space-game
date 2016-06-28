package com.github.pozo.game.server.model.unit.time.modelevents.types;

import com.github.pozo.game.server.model.unit.time.TimeUnits;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeEventTypes;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeModelEvent;

/**
 * Created by pozo on 2016.06.06..
 */
public class TimeChange extends TimeModelEvent {
    private static final TimeEventTypes TYPE = TimeEventTypes.CHANGE;

    public TimeChange(TimeUnits eventData) {
        super(TYPE, eventData);
    }
}
