package com.epam.hotelbooking.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.epam.hotelbooking.entity.Room;

public class IdentifiableRoomRowMapperTest {

    private static final String ID_COLUMN = "id";
    private static final String CAPACITY_COLUMN = "capacity";
    private static final String TYPE_COLUMN = "type";
    private static final String NUMBER_COLUMN = "number";
    private static final String ROOM_PRICE_ID_COLUMN = "room_price_id";

    private static final Long ID = 62L;
    private static final Integer CAPACITY = 4;
    private static final String TYPE = "deluxe";
    private static final Integer NUMBER = 14;
    private static final Long ROOM_PRICE_ID = 3L;

    @Test
    public void testMapShouldReturnRoomWhenAllDataContainsInResultSetAndValid() throws  SQLException {
        // given
        Room expectedRoom = new Room.RoomBuilder().withId(ID)
                .withCapacity(CAPACITY)
                .withRoomClass(TYPE)
                .withNumber(NUMBER)
                .withRoomPriceId(ROOM_PRICE_ID)
                .build();
        IdentifiableRoomRowMapper identifiableRoomRowMapper = new IdentifiableRoomRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(ID_COLUMN)).thenReturn(ID);
        when(resultSet.getInt(CAPACITY_COLUMN)).thenReturn(CAPACITY);
        when(resultSet.getString(TYPE_COLUMN)).thenReturn(TYPE);
        when(resultSet.getInt(NUMBER_COLUMN)).thenReturn(NUMBER);
        when(resultSet.getLong(ROOM_PRICE_ID_COLUMN)).thenReturn(ROOM_PRICE_ID);
        // when
        Room actualRoom = identifiableRoomRowMapper.map(resultSet);
        // then
        assertEquals(expectedRoom, actualRoom);
    }

    // then
    @Test(expected = SQLException.class)
    public void testMapShouldThrowExceptionWhenAccessDatabaseError() throws SQLException {
        // given
        IdentifiableRoomRowMapper identifiableRoomRowMapper = new IdentifiableRoomRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(ID_COLUMN)).thenReturn(ID);
        when(resultSet.getInt(CAPACITY_COLUMN)).thenReturn(CAPACITY);
        when(resultSet.getString(TYPE_COLUMN)).thenReturn(TYPE);
        when(resultSet.getInt(NUMBER_COLUMN)).thenThrow(new SQLException());
        when(resultSet.getLong(ROOM_PRICE_ID_COLUMN)).thenReturn(ROOM_PRICE_ID);
        // when
        identifiableRoomRowMapper.map(resultSet);
    }
}
