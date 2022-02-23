package com.epam.hotelbooking.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.RequestsAmount;

public class RequestsAmountRowMapper implements RowMapper<RequestsAmount> {

    @Override
    public RequestsAmount map(ResultSet resultSet) throws SQLException {
        return new RequestsAmount(resultSet.getInt(1));
    }

}
