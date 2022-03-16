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
import com.epam.hotelbooking.exception.DaoException;

public class RoomPriceRowMapperTest {

    @Test
    public void testMapShouldReturnRoomPriceWhenAllDataContainsInResultSetAndValid() throws DaoException, SQLException {
        // given
        Long id = 2L;
        Date validFrom = Date.valueOf("2022-03-30");
        BigDecimal price = BigDecimal.valueOf(12.32);
        RoomPrice expectedRoomPrice = new RoomPrice(id, price, validFrom);
        RoomPriceRowMapper roomPriceRowMapper = new RoomPriceRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong("id")).thenReturn(2L);
        when(resultSet.getBigDecimal("price")).thenReturn(BigDecimal.valueOf(12.32));
        when(resultSet.getDate("valid_from")).thenReturn( Date.valueOf("2022-03-30"));
        // when
        RoomPrice actualRoomPrice = roomPriceRowMapper.map(resultSet);
        // then
        assertEquals(expectedRoomPrice, actualRoomPrice);
    }
}
