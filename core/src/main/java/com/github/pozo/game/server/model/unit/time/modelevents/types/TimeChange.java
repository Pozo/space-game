package com.github.pozo.game.server.model.unit.time.modelevents.types;

import com.github.pozo.game.server.model.unit.time.TimeUnits;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeEventTypes;
import com.github.pozo.game.server.model.unit.time.modelevents.TimeModelEvent;

public class TimeChange extends TimeModelEvent {
    private static final TimeEventTypes TYPE = TimeEventTypes.CHANGE;

    public TimeChange(TimeUnits eventData) {
        super(TYPE, eventData);
    }
}
