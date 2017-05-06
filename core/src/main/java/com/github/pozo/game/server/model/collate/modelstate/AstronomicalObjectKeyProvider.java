package com.github.pozo.game.server.model.collate.modelstate;

import com.github.pozo.game.server.model.collate.CollatorKeyProvider;
import com.github.pozo.game.server.model.objects.natural.AstronomicalObject;


class AstronomicalObjectKeyProvider implements CollatorKeyProvider<AstronomicalObject> {
    private final GenericKeyFactory genericKeyFactory = new GenericKeyFactory();

    public String getKey(AstronomicalObject subject) {
        return genericKeyFactory.getTypeID(subject.getClass());
    }
}
