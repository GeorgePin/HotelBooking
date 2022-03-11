package com.epam.hotelbooking.service;

import java.util.Optional;

import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.ServiceException;

public interface UserService {

    Optional<User> login(String login, String password) throws ServiceException;

    void banUser(Long userId) throws ServiceException;

    void createUser(User user) throws ServiceException;

    ItemsTransferObject getAllClients(int pageNumber) throws ServiceException;

}
