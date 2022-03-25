package com.epam.hotelbooking.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.Room;

public class IdentifiableRoomRowMapper implements RowMapper<Room> {

    @Override
    public Room map(ResultSet resultSet) throws SQLException {
        try {
            Long roomId = resultSet.getLong("id");
            int capacity = resultSet.getInt("capacity");
            String roomClass = resultSet.getString("type");
            int number = resultSet.getInt("number");
            Long roomPriceId = resultSet.getLong("room_price_id");
            return new Room.RoomBuilder().withId(roomId)
                    .withCapacity(capacity)
                    .withRoomClass(roomClass)
                    .withNumber(number)
                    .withRoomPriceId(roomPriceId)
                    .build();
        } catch (SQLException exception) {
            throw new SQLException("Error during getting data from db", exception);
        }
    }
}