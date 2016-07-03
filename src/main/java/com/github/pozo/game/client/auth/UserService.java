package com.github.pozo.game.client.auth;

import com.github.pozo.game.client.RepositoryKey;

/**
 * Created by pozo on 2016.06.19..
 */
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String userName, String password) {
        RepositoryKey<String> repositoryKey = new RepositoryKey<String>(userName);
        User user = userRepository.find(repositoryKey);
        return user;
    }

    public boolean logout(User user) {
        return true;
    }
}
