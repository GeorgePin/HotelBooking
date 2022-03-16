package com.epam.hotelbooking.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.entity.RoomClass;
import com.epam.hotelbooking.exception.DaoException;

public class ClientRequestRowMapperTest {

    @Test
    public void testMapShouldReturnRequestWhenAllDataContainsInResultSetAndValid() throws DaoException, SQLException {
        // given
        Long id = 12L;
        Long userId = 17L;
        Long roomId = 3L;
        Date startDate = Date.valueOf("2022-03-03");
        Date endDate = Date.valueOf("2022-03-10");
        int roomCapacity = 3;
        RoomClass roomClass = RoomClass.DELUXE;
        boolean isApproved = true;
        BigDecimal price = BigDecimal.valueOf(12.99);
        Request expectedRequest = new Request(id, roomId, userId, startDate, endDate, roomCapacity, roomClass,
                isApproved, price);
        ClientRequestRowMapper clientRequestRowMapper = new ClientRequestRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong("id")).thenReturn(12L);
        when(resultSet.getLong("user_id")).thenReturn(17L);
        when(resultSet.getLong("room_id")).thenReturn(3L);
        when(resultSet.getDate("start_date")).thenReturn(Date.valueOf("2022-03-03"));
        when(resultSet.getDate("end_date")).thenReturn(Date.valueOf("2022-03-10"));
        when(resultSet.getInt("room_capacity")).thenReturn(3);
        when(resultSet.getString("room_class")).thenReturn("deluxe");
        when(resultSet.getBoolean("is_approved")).thenReturn(true);
        when(resultSet.getBigDecimal("price")).thenReturn(BigDecimal.valueOf(12.99));
        // when
        Request actualRequest = clientRequestRowMapper.map(resultSet);
        // then
        assertEquals(expectedRequest, actualRequest);
    }
}
