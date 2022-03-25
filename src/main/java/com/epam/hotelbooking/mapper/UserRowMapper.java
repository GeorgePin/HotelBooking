package com.epam.hotelbooking.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.User;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        try {
            Long id = resultSet.getLong("id");
            Boolean isAdmin = resultSet.getBoolean("is_admin");
            Boolean isBlocked = resultSet.getBoolean("is_blocked");
            return new User.UserBuilder().withId(id)
                    .withIsAdmin(isAdmin)
                    .withIsBlocked(isBlocked)
                    .build();
        } catch (SQLException exception) {
            throw new SQLException("Error during getting data from db", exception);
        }
    }
}