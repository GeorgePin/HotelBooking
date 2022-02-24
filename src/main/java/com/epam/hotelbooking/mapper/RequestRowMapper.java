package com.epam.hotelbooking.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.entity.RoomClass;

public class RequestRowMapper implements RowMapper<Request> {

    @Override
    public Request map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long roomId = resultSet.getLong("room_id");
        Long userId = resultSet.getLong("user_id");
        Date startDate = resultSet.getDate("start_date");
        Date endDate = resultSet.getDate("start_date");
        int roomCapacity = resultSet.getInt("room_capacity");
        RoomClass roomClass = RoomClass.valueOf(resultSet.getString("room_class")
                .toUpperCase());
        boolean isApproved = resultSet.getBoolean("is_approved");
        return new Request(id, roomId, userId, startDate, endDate, roomCapacity, roomClass, isApproved);
    }
}
