package com.github.pozo.game.server.serialize.json;

import com.github.pozo.game.server.control.event.user.GameUserEvent;
import com.github.pozo.game.server.control.event.user.GameUserEventType;
import com.github.pozo.game.server.control.event.user.UserEvent;
import com.github.pozo.game.server.model.ModelEvent;
import com.github.pozo.game.server.model.objects.artificial.ship.Ship;
import com.github.pozo.game.server.model.objects.artificial.ship.userevent.ShipUserEvent;
import com.github.pozo.game.server.model.objects.artificial.ship.userevent.ShipUserEventTypes;
import com.github.pozo.game.server.model.objects.meta.Player;
import com.github.pozo.game.server.model.objects.meta.PlayerProperties;
import com.github.pozo.game.server.model.unit.Coordinate;
import com.github.pozo.game.server.serialize.EventSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by pozo on 2016.06.14..
 */
public class JSONEventSerializer implements EventSerializer {
    private final Gson gson;

    public JSONEventSerializer() {
        this.gson = new GsonBuilder().registerTypeAdapter(Ship.class, new ShipSerializer())
                .registerTypeAdapter(UserEvent.class, new UserEventDeserializer())
                .create();

    }

    public String serialize(ModelEvent modelEvent) {
        return gson.toJson(modelEvent);
    }

    public String serialize(UserEvent modelEvent) {
        return gson.toJson(modelEvent);
    }

    public UserEvent deserialize(String finalMessage) {
        return gson.fromJson(finalMessage, UserEvent.class);
    }

    public class ShipSerializer implements JsonSerializer<Ship> {

        public JsonElement serialize(Ship ship, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("id", new JsonPrimitive(ship.getID()));
            jsonObject.add("coord", jsonSerializationContext.serialize(ship.getCoordinate()));
            jsonObject.add("route", jsonSerializationContext.serialize(ship.getDestinations()));
            return jsonObject;
        }
    }

    private class UserEventDeserializer implements JsonDeserializer<UserEvent> {

        public UserEvent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            JsonElement eventType = asJsonObject.get("eventType");
            JsonElement eventSubject = asJsonObject.get("eventSubject");
            JsonElement eventData = asJsonObject.get("eventData");

            if (ShipUserEventTypes.MOVE.toString().equals(eventType.getAsString())) {

                return ShipUserEvent.createShipUserEvent(ShipUserEventTypes.valueOf(eventType.getAsString()),
                        (Ship) jsonDeserializationContext.deserialize(eventSubject, Ship.class),
                        (Coordinate) jsonDeserializationContext.deserialize(eventData, Coordinate.class));
            } else if (GameUserEventType.LOGIN.toString().equals(eventType.getAsString())) {

                final Player player = (Player) jsonDeserializationContext.deserialize(eventSubject, Player.class);
                final PlayerProperties playerProperties = (PlayerProperties) jsonDeserializationContext.deserialize(eventData, PlayerProperties.class);

                return GameUserEvent.createLoginEvent(player,
                        (PlayerProperties) jsonDeserializationContext.deserialize(eventData, PlayerProperties.class));
            } else if (GameUserEventType.CHANGE_PREFERENCES.toString().equals(eventType.getAsString())) {
                final Player player = (Player) jsonDeserializationContext.deserialize(eventSubject, Player.class);
                final PlayerProperties playerProperties = (PlayerProperties) jsonDeserializationContext.deserialize(eventData, PlayerProperties.class);

                return GameUserEvent.createChangePreferencesEvent(player,
                        playerProperties);
            } else {
                return UserEvent.createEvent(null, null, null);
            }
        }
    }
}
