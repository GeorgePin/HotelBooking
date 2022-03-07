package com.epam.hotelbooking.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet) throws DaoException {
        try {
            Long id = resultSet.getLong("id");
            boolean isAdmin = resultSet.getBoolean("is_admin");
            return new User(id, isAdmin);
        } catch (SQLException exception) {
            throw new DaoException("Error during getting data from db", exception);
        }
    }
}