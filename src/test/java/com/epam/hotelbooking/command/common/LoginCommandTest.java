package com.epam.hotelbooking.command.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.entity.RoomClass;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.AdminRequestRowMapper;

public class LoginCommandTest {

    @Test
    public void testExecuteShouldReturnCommandResultWithIndexJspWhenUserIsClientAndLoginSuccessful()
            throws DaoException, SQLException {
        // given
        LoginCommand LoginCommand = new LoginCommand(null);
        Long id = 12L;
        Long userId = 17L;
        Date startDate = Date.valueOf("2022-03-03");
        Date endDate = Date.valueOf("2022-03-10");
        int roomCapacity = 3;
        RoomClass roomClass = RoomClass.DELUXE;
        Request expectedRequest = new Request(id, userId, startDate, endDate, roomCapacity, roomClass);
        AdminRequestRowMapper adminRequestRowMapper = new AdminRequestRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong("id")).thenReturn(12L);
        when(resultSet.getLong("user_id")).thenReturn(17L);
        when(resultSet.getDate("start_date")).thenReturn(Date.valueOf("2022-03-03"));
        when(resultSet.getDate("end_date")).thenReturn(Date.valueOf("2022-03-10"));
        when(resultSet.getInt("room_capacity")).thenReturn(3);
        when(resultSet.getString("room_class")).thenReturn("deluxe");
        // when
        Request actualRequest = adminRequestRowMapper.map(resultSet);
        // then
        assertEquals(expectedRequest, actualRequest);
    }
}
