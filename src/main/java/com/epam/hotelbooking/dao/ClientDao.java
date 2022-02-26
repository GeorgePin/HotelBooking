package com.epam.hotelbooking.dao;

import java.util.List;

import com.epam.hotelbooking.entity.User;

public interface ClientDao {
    List<User> getAllClients() throws Exception;
}
