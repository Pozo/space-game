package com.github.pozo;

import com.github.pozo.game.server.control.event.EventParser;
import com.github.pozo.game.server.control.event.user.UserEvent;
import com.github.pozo.game.server.serialize.json.JSONEventSerializer;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

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
