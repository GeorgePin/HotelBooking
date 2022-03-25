package com.epam.hotelbooking.mapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomPrice;

public class RoomWithPriceRowMapper implements RowMapper<Room> {

    @Override
    public Room map(ResultSet resultSet) throws SQLException {
        try {
            Long id = resultSet.getLong("id");
            Integer capacity = resultSet.getInt("capacity");
            String roomClass = resultSet.getString("type");
            Integer number = resultSet.getInt("number");
            Boolean isBlocked = resultSet.getBoolean("is_blocked");
            Long roomPriceId = resultSet.getLong("room_price_id");
            BigDecimal bigDecimalPrice = resultSet.getBigDecimal("price");
            Date priceValidFrom = resultSet.getDate("valid_from");
            RoomPrice entityPrice = new RoomPrice(roomPriceId, bigDecimalPrice, priceValidFrom);
            return new Room.RoomBuilder().withId(id)
                    .withCapacity(capacity)
                    .withRoomClass(roomClass)
                    .withNumber(number)
                    .withIsBlocked(isBlocked)
                    .withRoomPrice(entityPrice)
                    .build();
        } catch (SQLException exception) {
            throw new SQLException("Error during getting data from db", exception);
        }
    }
}