package com.epam.hotelbooking.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.hotelbooking.entity.Identifable;

public interface RowMapper<T extends Identifable> {
    T map(ResultSet resultSet) throws SQLException;
}
