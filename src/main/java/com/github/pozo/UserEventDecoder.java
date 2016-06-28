package com.github.pozo;

import com.github.pozo.game.server.control.EventParser;
import com.github.pozo.game.server.control.UserEvent;
import com.github.pozo.game.server.serialize.json.JSONEventSerializer;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Created by pozo on 2016.06.15..
 */
public class UserEventDecoder implements Decoder.Text<UserEvent> {
    private EventParser eventParser;

    public UserEvent decode(String message) throws DecodeException {
        return eventParser.parse(message);
    }

    public boolean willDecode(String message) {
        return true;
    }

    public void init(EndpointConfig config) {
        JSONEventSerializer eventSerializer = new JSONEventSerializer();
        this.eventParser = new EventParser(eventSerializer);
    }

    public void destroy() {

    }
}
