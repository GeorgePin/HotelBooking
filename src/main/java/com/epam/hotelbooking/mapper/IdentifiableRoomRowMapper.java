package com.epam.hotelbooking.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomClass;
import com.epam.hotelbooking.exception.DaoException;

public class IdentifiableRoomRowMapper implements RowMapper<Room> {

    @Override
    public Room map(ResultSet resultSet) throws DaoException {
        try {
            Long roomId = resultSet.getLong("id");
            int capacity = resultSet.getInt("capacity");
            RoomClass roomClass = RoomClass.valueOf(resultSet.getString("type")
                    .toUpperCase());
            int number = resultSet.getInt("number");
            Long roomPriceId = resultSet.getLong("room_price_id");
            return new Room(roomId, capacity, roomClass, number, roomPriceId);
        } catch (SQLException exception) {
            throw new DaoException("Error during getting data from db", exception);
        }
    }
}