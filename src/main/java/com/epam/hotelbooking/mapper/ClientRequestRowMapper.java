package com.epam.hotelbooking.mapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.Request;

public class ClientRequestRowMapper implements RowMapper<Request> {

    @Override
    public Request map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long roomId = resultSet.getLong("room_id");
        Long userId = resultSet.getLong("user_id");
        Date startDate = resultSet.getDate("start_date");
        Date endDate = resultSet.getDate("end_date");
        Integer roomCapacity = resultSet.getInt("room_capacity");
        String roomClass = resultSet.getString("room_class");
        Boolean isApproved = resultSet.getBoolean("is_approved");
        BigDecimal price = resultSet.getBigDecimal("price");
        return new Request.RequestBuilder().withId(id)
                .withRoomId(roomId)
                .withUserId(userId)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withRoomCapacity(roomCapacity)
                .withRoomClass(roomClass)
                .withIsApproved(isApproved)
                .withPrice(price)
                .build();
    }
}
