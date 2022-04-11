package com.epam.hotelbooking.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.epam.hotelbooking.entity.Request;

public class AdminRequestRowMapperTest {

    private static final String ID_COLUMN = "id";
    private static final String USER_ID_COLUMN = "user_id";
    private static final String START_DATE_COLUMN = "start_date";
    private static final String END_DATE_COLUMN = "end_date";
    private static final String ROOM_CAPACITY_COLUMN = "room_capacity";
    private static final String ROOM_CLASS_COLUMN = "room_class";

    private static final Long ID = 12L;
    private static final Long USER_ID = 17L;
    private static final Date START_DATE = Date.valueOf("2022-03-03");
    private static final Date END_DATE = Date.valueOf("2022-03-10");
    private static final int ROOM_CAPACITY = 3;
    private static final String ROOM_CLASS = "standart";

    @Test
    public void testMapShouldReturnRequestWhenAllDataContainsInResultSetAndValid() throws SQLException {
        // given
        Request expectedRequest = new Request.RequestBuilder().withId(ID)
                .withUserId(USER_ID)
                .withStartDate(START_DATE)
                .withEndDate(END_DATE)
                .withRoomCapacity(ROOM_CAPACITY)
                .withRoomClass(ROOM_CLASS)
                .build();
        AdminRequestRowMapper adminRequestRowMapper = new AdminRequestRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(ID_COLUMN)).thenReturn(ID);
        when(resultSet.getLong(USER_ID_COLUMN)).thenReturn(USER_ID);
        when(resultSet.getDate(START_DATE_COLUMN)).thenReturn(START_DATE);
        when(resultSet.getDate(END_DATE_COLUMN)).thenReturn(END_DATE);
        when(resultSet.getInt(ROOM_CAPACITY_COLUMN)).thenReturn(ROOM_CAPACITY);
        when(resultSet.getString(ROOM_CLASS_COLUMN)).thenReturn(ROOM_CLASS);
        // when
        Request actualRequest = adminRequestRowMapper.map(resultSet);
        // then
        assertEquals(expectedRequest, actualRequest);
    }

    // then
    @Test(expected = SQLException.class)
    public void testMapShouldThrowExceptionWhenAccessDatabaseError() throws SQLException {
        // given
        AdminRequestRowMapper adminRequestRowMapper = new AdminRequestRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong(ID_COLUMN)).thenThrow(new SQLException());
        when(resultSet.getLong(USER_ID_COLUMN)).thenReturn(USER_ID);
        when(resultSet.getDate(START_DATE_COLUMN)).thenReturn(START_DATE);
        when(resultSet.getDate(END_DATE_COLUMN)).thenReturn(END_DATE);
        when(resultSet.getInt(ROOM_CAPACITY_COLUMN)).thenReturn(ROOM_CAPACITY);
        when(resultSet.getString(ROOM_CLASS_COLUMN)).thenReturn(ROOM_CLASS);
        // when
        adminRequestRowMapper.map(resultSet);
    }
}