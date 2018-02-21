package io.jcervelin.ideas.service;

import io.jcervelin.ideas.entities.User;

import java.util.List;

public interface UserService {
    User findUserByEmail(String email);

    void saveUser(User user);

    List<User> findAll();
}
