package com.epam.hotelbooking.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.Request;

public class AdminRequestRowMapper implements RowMapper<Request> {

    @Override
    public Request map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long userId = resultSet.getLong("user_id");
        Date startDate = resultSet.getDate("start_date");
        Date endDate = resultSet.getDate("end_date");
        Integer roomCapacity = resultSet.getInt("room_capacity");
        String roomClass = resultSet.getString("room_class");
        return new Request.RequestBuilder().withId(id)
                .withUserId(userId)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withRoomCapacity(roomCapacity)
                .withRoomClass(roomClass)
                .build();
    }
}