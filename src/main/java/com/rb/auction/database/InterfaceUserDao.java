package com.rb.auction.database;

import com.rb.auction.model.User;

import java.util.Optional;

public interface InterfaceUserDao {
    void addUser(User user);
    Optional<User> getUserById(int id);
    Optional<User> getUserByLogin(String login);
}
