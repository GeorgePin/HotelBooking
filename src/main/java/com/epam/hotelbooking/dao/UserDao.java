package com.epam.hotelbooking.dao;

import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;

public interface UserDao {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    void banUser(Long userId) throws DaoException;

    Integer getAmountOfPagesWithCleints() throws DaoException;

    List<User> getAllClients(int pageNumber) throws DaoException;
}
