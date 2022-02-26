package com.epam.hotelbooking.service;

import java.util.Optional;

import com.epam.hotelbooking.entity.User;

public interface UserService {
    Optional<User> login(String login, String password) throws Exception;

    boolean banUser(Long userId) throws Exception;
}
