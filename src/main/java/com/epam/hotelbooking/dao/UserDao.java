package com.epam.hotelbooking.dao;

import java.util.Optional;

import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;

public interface UserDao {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException, Exception;

    public boolean createUser(String userName, String userSurname, String login, String password) throws Exception;

}
