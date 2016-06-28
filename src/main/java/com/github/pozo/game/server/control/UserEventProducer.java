package com.github.pozo.game.server.control;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pozo on 2016.06.05..
 */
public class UserEventProducer<T extends UserEvent> {
    private final List<UserEventConsumer> modelEventConsumerList = new ArrayList<UserEventConsumer>();

    public void addConsumer(UserEventConsumer modelEventConsumer) {
        modelEventConsumerList.add(modelEventConsumer);
    }
    public void addAllConsumer(List<UserEventConsumer> modelEventConsumers) {
        modelEventConsumerList.addAll(modelEventConsumers);
    }
    public void removeConsumer(UserEventConsumer modelEventConsumer) {
        modelEventConsumerList.remove(modelEventConsumer);
    }

    public List<UserEventConsumer> getConsumers() {
        return modelEventConsumerList;
    }

    public void produce(T event) {
        for (UserEventConsumer modelEventConsumer : getConsumers()) {
            modelEventConsumer.consume(event);
        }
    }
}
