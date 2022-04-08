package com.epam.hotelbooking.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.epam.hotelbooking.entity.RoomPrice;

public class RoomPriceRowMapperTest {

    private static final String ID_COLUMN = "id";
    private static final String VALID_FROM_COLUMN = "valid_from";
    private static final String PRICE_COLUMN = "price";

    private static final Long ID = 43L;
    private static final Date VALID_FROM = Date.valueOf("2022-03-30");
    private static final BigDecimal PRICE = new BigDecimal(43.32);

    @Test
    public void testMapShouldReturnRoomPriceWhenAllDataContainsInResultSetAndValid() throws SQLException {
        // given
        RoomPrice expectedRoomPrice = new RoomPrice(ID, PRICE, VALID_FROM);
        RoomPriceRowMapper roomPriceRowMapper = new RoomPriceRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(ID_COLUMN)).thenReturn(ID);
        when(resultSet.getBigDecimal(PRICE_COLUMN)).thenReturn(PRICE);
        when(resultSet.getDate(VALID_FROM_COLUMN)).thenReturn(VALID_FROM);
        // when
        RoomPrice actualRoomPrice = roomPriceRowMapper.map(resultSet);
        // then
        assertEquals(expectedRoomPrice, actualRoomPrice);
    }

    // then
    @Test(expected = SQLException.class)
    public void testMapShouldThrowExceptionWhenAccessDatabaseError() throws SQLException {
        // given
        RoomPriceRowMapper roomPriceRowMapper = new RoomPriceRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(ID_COLUMN)).thenThrow(new SQLException());
        when(resultSet.getBigDecimal(PRICE_COLUMN)).thenReturn(PRICE);
        when(resultSet.getDate(VALID_FROM_COLUMN)).thenReturn(VALID_FROM);
        // when
        roomPriceRowMapper.map(resultSet);
    }
}