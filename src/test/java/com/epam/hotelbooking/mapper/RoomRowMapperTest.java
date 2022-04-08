package com.epam.hotelbooking.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.DaoException;

public class RoomRowMapperTest {

    private static final String CAPACITY_COLUMN = "capacity";
    private static final String TYPE_COLUMN = "type";
    private static final String NUMBER_COLUMN = "number";
    private static final String ROOM_PRICE_ID_COLUMN = "room_price_id";

    private static final Integer CAPACITY = 3;
    private static final String TYPE = "deluxe";
    private static final Integer NUMBER = 14;
    private static final Long ROOM_PRICE_ID = 3L;

    @Test
    public void testMapShouldReturnRoomWhenAllDataContainsInResultSetAndValid() throws DaoException, SQLException {
        // given
        Room expectedRoom = new Room.RoomBuilder().withCapacity(CAPACITY)
                .withRoomClass(TYPE)
                .withNumber(NUMBER)
                .withRoomPriceId(ROOM_PRICE_ID)
                .build();
        RoomRowMapper roomRowMapper = new RoomRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt(CAPACITY_COLUMN)).thenReturn(CAPACITY);
        when(resultSet.getString(TYPE_COLUMN)).thenReturn(TYPE);
        when(resultSet.getInt(NUMBER_COLUMN)).thenReturn(NUMBER);
        when(resultSet.getLong(ROOM_PRICE_ID_COLUMN)).thenReturn(ROOM_PRICE_ID);
        // when
        Room actualRoom = roomRowMapper.map(resultSet);
        // then
        assertEquals(expectedRoom, actualRoom);
    }

    // then
    @Test(expected = SQLException.class)
    public void testMapShouldThrowExceptionWhenAccessDatabaseError() throws SQLException {
        // given
        RoomRowMapper roomRowMapper = new RoomRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt(CAPACITY_COLUMN)).thenReturn(CAPACITY);
        when(resultSet.getString(TYPE_COLUMN)).thenThrow(new SQLException());
        when(resultSet.getInt(NUMBER_COLUMN)).thenReturn(NUMBER);
        when(resultSet.getLong(ROOM_PRICE_ID_COLUMN)).thenReturn(ROOM_PRICE_ID);
        // when
        roomRowMapper.map(resultSet);
    }
}