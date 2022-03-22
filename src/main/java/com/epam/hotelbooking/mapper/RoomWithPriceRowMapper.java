package com.epam.hotelbooking.mapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomClass;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;

public class RoomWithPriceRowMapper implements RowMapper<Room> {

    @Override
    public Room map(ResultSet resultSet) throws DaoException {
        try {
            Long id = resultSet.getLong("id");
            int capacity = resultSet.getInt("capacity");
            RoomClass roomClass = RoomClass.valueOf(resultSet.getString("type")
                    .toUpperCase());
            int number = resultSet.getInt("number");
            boolean isBlocked = resultSet.getBoolean("is_blocked");
            Long roomPriceId = resultSet.getLong("room_price_id");
            BigDecimal bigDecimalPrice = resultSet.getBigDecimal("price");
            Date priceValidFrom = resultSet.getDate("valid_from");
            RoomPrice entityPrice = new RoomPrice(roomPriceId, bigDecimalPrice, priceValidFrom);
            return new Room(id, capacity, roomClass, number, isBlocked, entityPrice);
        } catch (SQLException exception) {
            throw new DaoException("Error during getting data from db", exception);
        }
    }
}