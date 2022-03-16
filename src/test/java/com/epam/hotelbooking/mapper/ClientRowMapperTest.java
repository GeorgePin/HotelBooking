package com.epam.hotelbooking.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;

public class ClientRowMapperTest {

    @Test
    public void testMapShouldReturnClientWhenAllDataContainsInResultSetAndValid() throws DaoException, SQLException {
        // given
        Long id = 90L;
        String login = "vasya1999";
        String name = "Vasya";
        String surname = "Pavlov";
        boolean isBlocked = false;
        User expectedUser = new User(id, name, surname, login, isBlocked);
        ClientRowMapper clientRowMapper = new ClientRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong("id")).thenReturn(90L);
        when(resultSet.getString("login")).thenReturn("vasya1999");
        when(resultSet.getString("name")).thenReturn("Vasya");
        when(resultSet.getString("surname")).thenReturn("Pavlov");
        when(resultSet.getBoolean("is_blocked")).thenReturn(false);
        // when
        User actualUser = clientRowMapper.map(resultSet);
        // then
        assertEquals(expectedUser, actualUser);
    }
}
