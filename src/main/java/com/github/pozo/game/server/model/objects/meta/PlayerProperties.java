package com.github.pozo.game.server.model.objects.meta;

import com.github.pozo.game.server.model.unit.Coordinate;

/**
 * Created by pozo on 2016.06.16..
 */
public class PlayerProperties {
    private int screenWidth;
    private int screenHeight;
    private Coordinate screenPosition;

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public Coordinate getScreenPosition() {
        return screenPosition;
    }

    public void setScreenPosition(Coordinate screenPosition) {
        this.screenPosition = screenPosition;
    }
}
