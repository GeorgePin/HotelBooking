package com.epam.hotelbooking.mapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.entity.RoomClass;
import com.epam.hotelbooking.exception.DaoException;

public class ClientRequestRowMapper implements RowMapper<Request> {

    @Override
    public Request map(ResultSet resultSet) throws DaoException {
        try {
            Long id = resultSet.getLong("id");
            Long roomId = resultSet.getLong("room_id");
            Long userId = resultSet.getLong("user_id");
            Date startDate = resultSet.getDate("start_date");
            Date endDate = resultSet.getDate("end_date");
            int roomCapacity = resultSet.getInt("room_capacity");
            RoomClass roomClass = RoomClass.valueOf(resultSet.getString("room_class")
                    .toUpperCase());
            boolean isApproved = resultSet.getBoolean("is_approved");
            BigDecimal price = resultSet.getBigDecimal("price");
            return new Request(id, roomId, userId, startDate, endDate, roomCapacity, roomClass, isApproved, price);
        } catch (SQLException exception) {
            throw new DaoException("Error during getting data from db", exception);
        }
    }
}
