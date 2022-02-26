package com.epam.hotelbooking.service;

import java.util.List;

import com.epam.hotelbooking.entity.User;

public interface ClientService {
    List<User> getAllClients() throws Exception;
}
