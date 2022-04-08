package com.epam.hotelbooking.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.epam.hotelbooking.entity.User;

public class ClientRowMapperTest {

    private static final String ID_COLUMN = "id";
    private static final String LOGIN_COLUMN = "login";
    private static final String NAME_COLUMN = "name";
    private static final String SURNAME_COLUMN = "surname";
    private static final String IS_BLOCKED_COLUMN = "is_blocked";

    private static final Long ID = 12L;
    private static final String LOGIN = "Vasya123";
    private static final String NAME = "Vasya";
    private static final String SURNAME = "Ivanov";
    private static final boolean IS_BLOCKED = false;

    @Test
    public void testMapShouldReturnClientWhenAllDataContainsInResultSetAndValid() throws  SQLException {
        // given
        User expectedUser = new User.UserBuilder().withId(ID)
                .withLogin(LOGIN)
                .withName(NAME)
                .withSurname(SURNAME)
                .withIsBlocked(IS_BLOCKED)
                .build();
        ClientRowMapper clientRowMapper = new ClientRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(ID_COLUMN)).thenReturn(ID);
        when(resultSet.getString(LOGIN_COLUMN)).thenReturn(LOGIN);
        when(resultSet.getString(NAME_COLUMN)).thenReturn(NAME);
        when(resultSet.getString(SURNAME_COLUMN)).thenReturn(SURNAME);
        when(resultSet.getBoolean(IS_BLOCKED_COLUMN)).thenReturn(IS_BLOCKED);
        // when
        User actualUser = clientRowMapper.map(resultSet);
        // then
        assertEquals(expectedUser, actualUser);
    }

    // then
    @Test(expected = SQLException.class)
    public void testMapShouldThrowExceptionWhenAccessDatabaseError() throws SQLException {
        // given
        ClientRowMapper clientRowMapper = new ClientRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(ID_COLUMN)).thenReturn(ID);
        when(resultSet.getString(LOGIN_COLUMN)).thenThrow(new SQLException());
        when(resultSet.getString(NAME_COLUMN)).thenReturn(NAME);
        when(resultSet.getString(SURNAME_COLUMN)).thenReturn(SURNAME);
        when(resultSet.getBoolean(IS_BLOCKED_COLUMN)).thenReturn(IS_BLOCKED);
        // when
        clientRowMapper.map(resultSet);
    }
}