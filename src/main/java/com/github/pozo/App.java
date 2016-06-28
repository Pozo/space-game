package com.github.pozo;

import com.github.pozo.game.client.GameClient;
import com.github.pozo.game.server.Game;
import com.github.pozo.game.server.GameServer;

import org.glassfish.tyrus.server.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws InterruptedException {
        GameClient gameClient = new GameClient();
        Server gameServer = new Server("localhost", 8025, "/", null, GameServer.class);

        try {
            initializeGameModel();
            gameServer.start();
            gameClient.run(args);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please press a key to stop the gameServer.");
            reader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            gameServer.stop();
        }
    }

    private static void initializeGameModel() {
        Game.getInstance().start();
    }
}
