package com.github.pozo.game.server.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pozo on 2016.06.05..
 */
public abstract class ModelEventProducer<T extends ModelEvent> {
    private final List<ModelEventConsumer> modelEventConsumerList = new ArrayList<ModelEventConsumer>();

    public void addConsumer(ModelEventConsumer modelEventConsumer) {
        modelEventConsumerList.add(modelEventConsumer);
    }
    public void addAllConsumer(List<ModelEventConsumer> modelEventConsumers) {
        modelEventConsumerList.addAll(modelEventConsumers);
    }
    public void removeConsumer(ModelEventConsumer modelEventConsumer) {
        modelEventConsumerList.remove(modelEventConsumer);
    }

    public List<ModelEventConsumer> getConsumers() {
        return modelEventConsumerList;
    }
    public abstract void produce(T event);
}
