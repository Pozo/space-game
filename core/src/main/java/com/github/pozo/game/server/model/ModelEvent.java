package com.github.pozo.game.server.model;

public abstract class ModelEvent<T extends ModelEventType, D> {
    private final T eventType;
    private final D eventData;

    public ModelEvent(T eventType, D eventData) {
        this.eventType = eventType;
        this.eventData = eventData;
    }

    public T getType() {
        return eventType;
    }

    public D getData() {
        return eventData;
    }

    @Override
    public String toString() {
        return "ModelEvent{" +
                "eventType=" + eventType +
                ", eventData=" + eventData +
                '}';
    }
}
