package com.epam.hotelbooking.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.Room;

public class RoomRowMapper implements RowMapper<Room> {

    @Override
    public Room map(ResultSet resultSet) throws SQLException {
        int capacity = resultSet.getInt("capacity");
        String roomClass = resultSet.getString("type");
        int number = resultSet.getInt("number");
        Long roomPriceId = resultSet.getLong("room_price_id");
        return new Room.RoomBuilder().withCapacity(capacity)
                .withRoomClass(roomClass)
                .withNumber(number)
                .withRoomPriceId(roomPriceId)
                .build();
    }
}