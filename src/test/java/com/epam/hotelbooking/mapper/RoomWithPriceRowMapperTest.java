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
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;

public class RoomWithPriceRowMapperTest {

    private static final String ID_COLUMN = "id";
    private static final String CAPACITY_COLUMN = "capacity";
    private static final String TYPE_COLUMN = "type";
    private static final String NUMBER_COLUMN = "number";
    private static final String ROOM_PRICE_ID_COLUMN = "room_price_id";
    private static final String PRICE_COLUMN = "price";
    private static final String VALID_FROM_COLUMN = "valid_from";
    private static final String IS_BLOCKED_COLUMN = "is_blocked";

    private static final Long ID = 5L;
    private static final Integer CAPACITY = 3;
    private static final String TYPE = "deluxe";
    private static final Integer NUMBER = 14;
    private static final Long ROOM_PRICE_ID = 3L;
    private static final BigDecimal PRICE = new BigDecimal(43.99);
    private static final Date VALID_FROM = Date.valueOf("2022-02-10");
    private static final boolean IS_BLOCKED = false;

    @Test
    public void testMapShouldReturnRoomWithPriceWhenAllDataContainsInResultSetAndValid()
            throws DaoException, SQLException {
        // given
        RoomPrice roomPrice = new RoomPrice(ROOM_PRICE_ID, PRICE, VALID_FROM);
        Room expectedRoom = new Room.RoomBuilder().withId(ID)
                .withCapacity(CAPACITY)
                .withRoomClass(TYPE)
                .withNumber(NUMBER)
                .withIsBlocked(IS_BLOCKED)
                .withRoomPrice(roomPrice)
                .build();
        RoomWithPriceRowMapper roomWithPriceRowMapper = new RoomWithPriceRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(ID_COLUMN)).thenReturn(ID);
        when(resultSet.getInt(CAPACITY_COLUMN)).thenReturn(CAPACITY);
        when(resultSet.getString(TYPE_COLUMN)).thenReturn(TYPE);
        when(resultSet.getInt(NUMBER_COLUMN)).thenReturn(NUMBER);
        when(resultSet.getBoolean(IS_BLOCKED_COLUMN)).thenReturn(IS_BLOCKED);
        when(resultSet.getBigDecimal(PRICE_COLUMN)).thenReturn(PRICE);
        when(resultSet.getLong(ROOM_PRICE_ID_COLUMN)).thenReturn(ROOM_PRICE_ID);
        when(resultSet.getDate(VALID_FROM_COLUMN)).thenReturn(VALID_FROM);
        // when
        Room actualRoom = roomWithPriceRowMapper.map(resultSet);
        // then
        assertEquals(expectedRoom, actualRoom);
    }

    // then
    @Test(expected = SQLException.class)
    public void testMapShouldThrowExceptionWhenAccessDatabaseError() throws SQLException {
        // given
        RoomWithPriceRowMapper roomWithPriceRowMapper = new RoomWithPriceRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(ID_COLUMN)).thenReturn(ID);
        when(resultSet.getInt(CAPACITY_COLUMN)).thenReturn(CAPACITY);
        when(resultSet.getString(TYPE_COLUMN)).thenReturn(TYPE);
        when(resultSet.getInt(NUMBER_COLUMN)).thenThrow(new SQLException());
        when(resultSet.getBoolean(IS_BLOCKED_COLUMN)).thenReturn(IS_BLOCKED);
        when(resultSet.getBigDecimal(PRICE_COLUMN)).thenReturn(PRICE);
        when(resultSet.getLong(ROOM_PRICE_ID_COLUMN)).thenReturn(ROOM_PRICE_ID);
        when(resultSet.getDate(VALID_FROM_COLUMN)).thenReturn(VALID_FROM);
        // when
        roomWithPriceRowMapper.map(resultSet);
    }
}
