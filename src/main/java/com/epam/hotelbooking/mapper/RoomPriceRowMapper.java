package com.epam.hotelbooking.mapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;

public class RoomPriceRowMapper implements RowMapper<RoomPrice> {

    @Override
    public RoomPrice map(ResultSet resultSet) throws DaoException {
        try {
            Long id = resultSet.getLong("id");
            BigDecimal price = resultSet.getBigDecimal("price");
            Date validFrom = resultSet.getDate("valid_from");
            return new RoomPrice(id, price, validFrom);
        } catch (SQLException exception) {
            throw new DaoException("Error during getting data from db", exception);
        }
    }
}