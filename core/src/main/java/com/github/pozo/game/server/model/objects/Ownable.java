package com.github.pozo.game.server.model.objects;

/**
 * Created by pozo on 2016.06.07..
 */
public interface Ownable {
    boolean hasOwner();

    void removeOwner();

    Owner getOwner();

    void setOwner(Owner owner);
}
