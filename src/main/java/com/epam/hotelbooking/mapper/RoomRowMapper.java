package com.epam.hotelbooking.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomClass;
import com.epam.hotelbooking.entity.RoomPrice;

public class RoomRowMapper implements RowMapper<Room> {

    @Override
    public Room map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        int capacity = resultSet.getInt("capacity");
        RoomClass roomClass = RoomClass.valueOf(resultSet.getString("type")
                .toUpperCase());
        int number = resultSet.getInt("number");
        boolean isBlocked = resultSet.getBoolean("is_blocked");
        RoomPrice roomPrice = new RoomPrice("room_price_id","room_price_price","room_price_valid_from");
        return new Room(id, capacity, roomClass, number, isBlocked, roomPrice);
    }
}
