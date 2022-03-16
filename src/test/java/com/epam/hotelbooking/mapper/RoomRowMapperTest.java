package com.epam.hotelbooking.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomClass;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;

public class RoomRowMapperTest {

    @Test
    public void testMapShouldReturnRoomWhenAllDataContainsInResultSetAndValid() throws DaoException, SQLException {
        // given
        Long id = 23L;
        int capacity = 4;
        RoomClass roomClass = RoomClass.PREMIUM;
        int number = 321;
        boolean isBlocked = true;
        RoomPrice roomPrice = new RoomPrice(1L, BigDecimal.valueOf(12.32), Date.valueOf("2022-03-30"));
        Room expectedRoom = new Room(id, capacity, roomClass, number, isBlocked, roomPrice);
        RoomRowMapper roomRowMapper = new RoomRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong("id")).thenReturn(23L);
        when(resultSet.getInt("capacity")).thenReturn(4);
        when(resultSet.getString("type")).thenReturn("premium");
        when(resultSet.getInt("number")).thenReturn(321);
        when(resultSet.getBoolean("is_blocked")).thenReturn(true);
        when(resultSet.getLong("room_price_id")).thenReturn(1L);
        when(resultSet.getBigDecimal("price")).thenReturn(BigDecimal.valueOf(12.32));
        when(resultSet.getDate("valid_from")).thenReturn(Date.valueOf("2022-03-30"));
        // when
        Room actualRoom = roomRowMapper.map(resultSet);
        // then
        assertEquals(expectedRoom, actualRoom);
    }
}
