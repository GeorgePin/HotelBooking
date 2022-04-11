package com.epam.hotelbooking.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.User;

public class ClientRowMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String login = resultSet.getString("login");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        Boolean isBlocked = resultSet.getBoolean("is_blocked");
        return new User.UserBuilder().withId(id)
                .withLogin(login)
                .withName(name)
                .withSurname(surname)
                .withIsBlocked(isBlocked)
                .build();
    }
}