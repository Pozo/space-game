package com.github.pozo.game.server.control;

/**
 * Created by pozo on 2016.06.13..
 */
public class UserEvent<T extends UserEventType, S, D> {
    private final T eventType;
    private final S eventSubject;
    private final D eventData;

    public UserEvent(T eventType, S eventSubject, D eventData) {
        this.eventType = eventType;
        this.eventSubject = eventSubject;
        this.eventData = eventData;
    }
    public T getType(){
        return eventType;
    }
    public D getData(){
        return eventData;
    }

    public S getEventSubject() {
        return eventSubject;
    }

    public static <T extends UserEventType,S,D> UserEvent createEvent(T eventTypes, S ship,D newCoordinate) {
        return new UserEvent<T,S,D>(eventTypes, ship, newCoordinate);
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "eventType=" + eventType +
                ", eventData=" + eventData +
                '}';
    }

}
