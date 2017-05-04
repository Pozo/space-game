package com.github.pozo.game.server.model.collate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pozo on 2016.07.02..
 */
public class Collator<T> {
    private final Map<String, List<T>> collatedElements = new HashMap<String, List<T>>();
    private final CollatorKeyProvider<T> collatorKeyProvider;

    public Collator(CollatorKeyProvider<T> collatorKeyProvider) {
        this.collatorKeyProvider = collatorKeyProvider;
    }

    public Map<String, List<T>> collate(T needToBeCollated) {
        String key = collatorKeyProvider.getKey(needToBeCollated);

        if (collatedElements.containsKey(key)) {
            collatedElements.get(key).add(needToBeCollated);
        } else {
            ArrayList<T> objects = new ArrayList<T>();
            objects.add(needToBeCollated);
            collatedElements.put(key, objects);
        }

        return collatedElements;
    }
}
