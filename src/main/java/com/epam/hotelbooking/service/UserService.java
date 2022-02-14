package com.epam.hotelbooking.service;

import java.util.Optional;

import com.epam.hotelbooking.entity.User;

public interface UserService {
    public Optional<User> login(String login, String password) throws Exception;
}
