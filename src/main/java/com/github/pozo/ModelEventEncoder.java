package com.github.pozo;

import com.github.pozo.game.server.model.ModelEvent;
import com.github.pozo.game.server.serialize.json.JSONEventSerializer;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by pozo on 2016.06.15..
 */
public class ModelEventEncoder implements Encoder.Text<ModelEvent> {
    private JSONEventSerializer eventSerializer;

    public String encode(ModelEvent event) throws EncodeException {
        return eventSerializer.serialize(event);
    }

    public void init(EndpointConfig config) {
        eventSerializer = new JSONEventSerializer();
    }

    public void destroy() {

    }
}
