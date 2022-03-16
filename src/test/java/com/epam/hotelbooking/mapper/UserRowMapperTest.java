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

    @Test
    public void testMapShouldReturnUserWhenAllDataContainsInResultSetAndValid() throws DaoException, SQLException {
        // given
        Long id = 95L;
        boolean isAdmin = true;
        boolean isBlocked = false;
        User expectedUser = new User(id, isAdmin, isBlocked);
        UserRowMapper userRowMapper = new UserRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong("id")).thenReturn(95L);
        when(resultSet.getBoolean("is_admin")).thenReturn(true);
        when(resultSet.getBoolean("is_blocked")).thenReturn(false);
        // when
        User actualUser = userRowMapper.map(resultSet);
        // then
        assertEquals(expectedUser, actualUser);
    }
}
