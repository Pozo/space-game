package com.github.pozo.game.server.model.collate.modelstate;

import com.github.pozo.game.server.model.collate.CollatorKeyProvider;
import com.github.pozo.game.server.model.objects.artificial.ArtificialObject;


class ArtificialObjectKeyProvider implements CollatorKeyProvider<ArtificialObject> {
    private final GenericKeyFactory genericKeyFactory = new GenericKeyFactory();

    public String getKey(ArtificialObject subject) {
        return genericKeyFactory.getTypeID(subject.getClass());
    }
}
