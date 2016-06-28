package com.github.pozo.game.server;

import com.github.pozo.game.server.model.unit.time.TimeUnits;
import com.github.pozo.game.server.model.unit.time.modelevents.types.TimeChange;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by pozo on 2016.05.25..
 */
public class Game {
    private static Game instance;

    private final Clock clock;
    private final GameModel gameModel;

    public static final int PHYSICS_LOOP = 15;
    public static final int UPDATE_LOOP = 45;
    private boolean isRunning;

    public static Game getInstance() {
        if (instance == null) {
            synchronized (Game.class) {
                if (instance == null) {
                    instance = createGameWithDefaultParameters();
                }
            }
        }
        return instance;
    }

    private Game(GameModel gameModel, Clock clock) {
        this.gameModel = gameModel;
        this.clock = clock;
    }
    private static Game createGameWithDefaultParameters() {
        final Clock clock = new Clock();
        final GameModel gameModel = new GameModel();
        return new Game(gameModel, clock);
    }
    private void startUpdateScheduler() {
        final ScheduledExecutorService updateScheduler = Executors.newScheduledThreadPool(1);
        updateScheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try{
                    if(isRunning()) {

                    } else {
                        updateScheduler.shutdown();
                    }
                } catch(Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }, UPDATE_LOOP, UPDATE_LOOP, TimeUnit.MILLISECONDS);
    }

    private void startPhisicsScheduler() {
        final ScheduledExecutorService physicsScheduler = Executors.newScheduledThreadPool(1);
        physicsScheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    if (isRunning()) {
                        TimeChange timeEvent = new TimeChange(new TimeUnits(1));
                        clock.produce(timeEvent);
                    } else {
                        physicsScheduler.shutdown();
                    }
                } catch(Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }, PHYSICS_LOOP, PHYSICS_LOOP, TimeUnit.MILLISECONDS);
    }
    public void start() {
        clock.addAllConsumer(gameModel.getModelEventConsumers());

        setIsRunning(true);

        startPhisicsScheduler();
        startUpdateScheduler();

    }
    private void stop() {
        setIsRunning(false);
    }
    public GameModel getGameModel() {
        return gameModel;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
