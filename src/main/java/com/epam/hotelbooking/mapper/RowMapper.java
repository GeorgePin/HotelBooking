package com.epam.hotelbooking.mapper;

import java.sql.ResultSet;

import com.epam.hotelbooking.entity.Entity;
import com.epam.hotelbooking.exception.DaoException;

public interface RowMapper<T extends Entity> {
    T map(ResultSet resultSet) throws DaoException;
}
