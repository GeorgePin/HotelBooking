package com.epam.hotelbooking.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;

public class UserRowMapperTest {

    private static final String ID_COLUMN = "id";
    private static final String IS_ADMIN_COLUMN = "is_admin";
    private static final String IS_BLOCKED_COLUMN = "is_blocked";

    private static final Long ID = 14L;
    private static final boolean IS_ADMIN = false;
    private static final boolean IS_BLOCKED = true;

    @Test
    public void testMapShouldReturnUserWhenAllDataContainsInResultSetAndValid() throws DaoException, SQLException {
        // given
        User expectedUser = new User.UserBuilder().withId(ID)
                .withIsAdmin(IS_ADMIN)
                .withIsBlocked(IS_BLOCKED)
                .build();
        UserRowMapper userRowMapper = new UserRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(ID_COLUMN)).thenReturn(ID);
        when(resultSet.getBoolean(IS_ADMIN_COLUMN)).thenReturn(IS_ADMIN);
        when(resultSet.getBoolean(IS_BLOCKED_COLUMN)).thenReturn(IS_BLOCKED);
        // when
        User actualUser = userRowMapper.map(resultSet);
        // then
        assertEquals(expectedUser, actualUser);
    }

    // then
    @Test(expected = SQLException.class)
    public void testMapShouldThrowExceptionWhenAccessDatabaseError() throws SQLException {
        // given
        UserRowMapper userRowMapper = new UserRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(ID_COLUMN)).thenReturn(ID);
        when(resultSet.getBoolean(IS_ADMIN_COLUMN)).thenReturn(IS_ADMIN);
        when(resultSet.getBoolean(IS_BLOCKED_COLUMN)).thenThrow(new SQLException());
        // when
        userRowMapper.map(resultSet);
    }
}