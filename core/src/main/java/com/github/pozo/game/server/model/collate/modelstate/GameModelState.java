package com.github.pozo.game.server.model.collate.modelstate;

import com.github.pozo.game.server.model.objects.artificial.ArtificialObject;
import com.github.pozo.game.server.model.objects.meta.PlayerProperties;
import com.github.pozo.game.server.model.objects.natural.AstronomicalObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameModelState {
    private PlayerProperties playerProperties;
    private Map<String, List<AstronomicalObject>> astronomicalObjects = new HashMap<String, List<AstronomicalObject>>();
    private Map<String, List<ArtificialObject>> artificialObjects = new HashMap<String, List<ArtificialObject>>();

    GameModelState() {

    }

    public void setAstronomicalObjects(Map<String, List<AstronomicalObject>> astronomicalObjects) {
        this.astronomicalObjects = astronomicalObjects;
    }

    public void setArtificialObjects(Map<String, List<ArtificialObject>> artificialObjects) {
        this.artificialObjects = artificialObjects;
    }

    public void setPlayerProperties(PlayerProperties playerProperties) {
        this.playerProperties = playerProperties;
    }
}
