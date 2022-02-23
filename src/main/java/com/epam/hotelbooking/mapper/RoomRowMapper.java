package com.epam.hotelbooking.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomClass;

public class RoomRowMapper implements RowMapper<Room> {

    @Override
    public Room map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        int capacity = resultSet.getInt("capacity");
        RoomClass roomClass = RoomClass.valueOf(resultSet.getString("type")
                .toUpperCase());
        int number = resultSet.getInt("number");
        boolean isBlocked = resultSet.getBoolean("is_blocked");
        BigDecimal roomPrice = resultSet.getBigDecimal("price");
        return new Room(id, capacity, roomClass, number, isBlocked, roomPrice);
    }
}
