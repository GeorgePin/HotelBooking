package com.epam.hotelbooking.dao;

import java.sql.SQLException;
import java.util.List;

import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.exception.DaoException;

public interface RequestsPageDao {
    List<Request> getRequests(int startElement) throws SQLException, DaoException;
}
