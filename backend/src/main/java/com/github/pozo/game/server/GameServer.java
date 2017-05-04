package com.github.pozo.game.server;

import com.github.pozo.ModelEventEncoder;
import com.github.pozo.UserEventDecoder;
import com.github.pozo.game.server.control.GameController;
import com.github.pozo.game.server.control.event.user.UserEvent;
import com.github.pozo.game.server.model.GameModel;
import com.github.pozo.game.server.model.ModelEvent;
import com.github.pozo.game.server.model.ModelEventConsumer;
import com.github.pozo.game.server.model.objects.meta.Player;
import com.github.pozo.game.server.model.objects.meta.PlayerId;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/",
        decoders = {UserEventDecoder.class},
        encoders = {ModelEventEncoder.class},
        configurator = GameServerConfigurator.class)
public class GameServer implements ModelEventConsumer<ModelEvent> {
    private Player player;
    private GameController gameController;
    private RemoteEndpoint.Basic remote;
    private RemoteEndpoint.Async asyncRemote;

    @OnMessage
    public String onMessage(UserEvent userEvent, Session session) {

        if (hasConnectedPlayer()) {
            gameController.dispatchEvent(userEvent);
        }

        return "{}";
    }

    private boolean hasConnectedPlayer() {
        return player != null;
    }

    @OnOpen
    public void open(Session session) {
        final Game game = Game.getInstance();
        final GameModel gameModel = game.getGameModel();


        this.remote = session.getBasicRemote();
        this.asyncRemote = session.getAsyncRemote();

        PlayerId jozsi = new PlayerId("Jozsi");
        this.player = gameModel.getPlayer(jozsi);
        this.player.setConsumerDelegate(GameServer.this);

        gameController = new GameController(this.player, gameModel);
        /*
        PlayerProperties playerSettings = new PlayerProperties();
        playerSettings.setScreenHeight(10);
        playerSettings.setScreenHeight(100);
        */

        //gameController.dispatchEvent(GameUserEvent.createLoginEvent(this.player, playerSettings));
    }

    @OnClose
    public void close(Session session) {
        try {
            session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "diconnected"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @OnError
    public void onError(Throwable error) {
        System.err.println("onError : error = " + error.getMessage());
    }

    public void consume(ModelEvent modelEvent) {

        if (asyncRemote != null) {
            asyncRemote.sendObject(modelEvent);
        }
    }
}
