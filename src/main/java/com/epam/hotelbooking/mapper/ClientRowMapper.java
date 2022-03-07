package com.epam.hotelbooking.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;

public class ClientRowMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet) throws DaoException {
        try {
            Long id = resultSet.getLong("id");
            String login = resultSet.getString("login");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            boolean isBlocked = resultSet.getBoolean("is_blocked");
            return new User(id, name, surname, login, isBlocked);
        } catch (SQLException exception) {
            throw new DaoException("Error during getting data from db", exception);
        }
    }
}