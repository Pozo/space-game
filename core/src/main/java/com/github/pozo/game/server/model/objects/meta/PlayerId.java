package com.github.pozo.game.server.model.objects.meta;

/**
 * Created by pozo on 2016.06.07..
 */
public class PlayerId {
    private final String id;

    public PlayerId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerId playerId = (PlayerId) o;

        return id.equals(playerId.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
