package com.github.pozo.game.client.auth;

import com.github.pozo.game.client.Repository;
import com.github.pozo.game.client.RepositoryKey;

import java.util.List;

/**
 * Created by pozo on 2016.06.19..
 */
public class UserRepository implements Repository<User,String> {

    public User find(RepositoryKey<String> repositoryKey) {
        String userName = repositoryKey.getKey();

        if(userName.equals("Jozsi")) {
            return new User("Jozsi","");
        } else if(userName.equals("Bela")) {
            return new User("Bela","");
        } else {
            throw new IllegalArgumentException("Invalid user");
        }
    }

    public List<User> findAll() {
        throw new IllegalArgumentException("Unsupported action");
    }

    public void add(User type) {
        throw new IllegalArgumentException("Unsupported action");
    }

    public void addAll(List<User> types) {
        throw new IllegalArgumentException("Unsupported action");
    }

    public void put(User type) {
        throw new IllegalArgumentException("Unsupported action");
    }

    public void putAll(List<User> types) {
        throw new IllegalArgumentException("Unsupported action");
    }

    public void delete(RepositoryKey<String> repositoryKey) {
        throw new IllegalArgumentException("Unsupported action");
    }

    public void deleteAll(List<RepositoryKey<String>> types) {
        throw new IllegalArgumentException("Unsupported action");
    }
}