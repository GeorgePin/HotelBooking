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

public class ClientRequestRowMapperTest {

    private static final String ID_COLUMN = "id";
    private static final String USER_ID_COLUMN = "user_id";
    private static final String START_DATE_COLUMN = "start_date";
    private static final String END_DATE_COLUMN = "end_date";
    private static final String ROOM_CAPACITY_COLUMN = "room_capacity";
    private static final String ROOM_CLASS_COLUMN = "room_class";
    private static final String ROOM_ID_COLUMN = "room_id";
    private static final String IS_APPROVED_COLUMN = "is_approved";
    private static final String PRICE_COLUMN = "price";

    private static final Long ID = 12L;
    private static final Long USER_ID = 17L;
    private static final Long ROOM_ID = 2L;
    private static final boolean IS_APPROVED = true;
    private static final BigDecimal PRICE = new BigDecimal(12.99);
    private static final Date START_DATE = Date.valueOf("2022-03-03");
    private static final Date END_DATE = Date.valueOf("2022-03-10");
    private static final int ROOM_CAPACITY = 3;
    private static final String ROOM_CLASS = "standart";

    @Test
    public void testMapShouldReturnRequestWhenAllDataContainsInResultSetAndValid() throws SQLException {
        // given
        Request expectedRequest = new Request.RequestBuilder().withId(ID)
                .withRoomId(ROOM_ID)
                .withUserId(USER_ID)
                .withStartDate(START_DATE)
                .withEndDate(END_DATE)
                .withRoomCapacity(ROOM_CAPACITY)
                .withRoomClass(ROOM_CLASS)
                .withIsApproved(IS_APPROVED)
                .withPrice(PRICE)
                .build();
        ClientRequestRowMapper clientRequestRowMapper = new ClientRequestRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(ID_COLUMN)).thenReturn(ID);
        when(resultSet.getLong(ROOM_ID_COLUMN)).thenReturn(ROOM_ID);
        when(resultSet.getBigDecimal(PRICE_COLUMN)).thenReturn(PRICE);
        when(resultSet.getBoolean(IS_APPROVED_COLUMN)).thenReturn(IS_APPROVED);
        when(resultSet.getLong(USER_ID_COLUMN)).thenReturn(USER_ID);
        when(resultSet.getDate(START_DATE_COLUMN)).thenReturn(START_DATE);
        when(resultSet.getDate(END_DATE_COLUMN)).thenReturn(END_DATE);
        when(resultSet.getInt(ROOM_CAPACITY_COLUMN)).thenReturn(ROOM_CAPACITY);
        when(resultSet.getString(ROOM_CLASS_COLUMN)).thenReturn(ROOM_CLASS);
        // when
        Request actualRequest = clientRequestRowMapper.map(resultSet);
        // then
        assertEquals(expectedRequest, actualRequest);
    }

    // then
    @Test(expected = SQLException.class)
    public void testMapShouldThrowExceptionWhenAccessDatabaseError() throws SQLException {
        // given
        ClientRequestRowMapper clientRequestRowMapper = new ClientRequestRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(ID_COLUMN)).thenReturn(ID);
        when(resultSet.getLong(ROOM_ID_COLUMN)).thenReturn(ROOM_ID);
        when(resultSet.getBigDecimal(PRICE_COLUMN)).thenReturn(PRICE);
        when(resultSet.getBoolean(IS_APPROVED_COLUMN)).thenReturn(IS_APPROVED);
        when(resultSet.getLong(USER_ID_COLUMN)).thenThrow(new SQLException());
        when(resultSet.getDate(START_DATE_COLUMN)).thenReturn(START_DATE);
        when(resultSet.getDate(END_DATE_COLUMN)).thenReturn(END_DATE);
        when(resultSet.getInt(ROOM_CAPACITY_COLUMN)).thenReturn(ROOM_CAPACITY);
        when(resultSet.getString(ROOM_CLASS_COLUMN)).thenReturn(ROOM_CLASS);
        // when
        clientRequestRowMapper.map(resultSet);
    }
}