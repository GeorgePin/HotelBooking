package com.epam.hotelbooking.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.RoomsAmount;

public class RoomsAmountRowMapper implements RowMapper<RoomsAmount> {

    @Override
    public RoomsAmount map(ResultSet resultSet) throws SQLException {
        return new RoomsAmount(resultSet.getInt(1));
    }

}
