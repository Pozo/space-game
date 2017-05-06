package com.github.pozo.game.server.model.objects;


public interface Ownable {
    boolean hasOwner();

    void removeOwner();

    Owner getOwner();

    void setOwner(Owner owner);
}
