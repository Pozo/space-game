package com.github.pozo.game.client;

import java.util.List;

/**
 * Created by pozo on 2016.06.19..
 */
public interface Repository<T, K> {
    T find(RepositoryKey<K> repositoryKey);

    List<T> findAll();

    void add(T type);

    void addAll(List<T> types);

    void put(T type);

    void putAll(List<T> types);

    void delete(RepositoryKey<K> repositoryKey);

    void deleteAll(List<RepositoryKey<K>> types);
}