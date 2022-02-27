package com.epam.hotelbooking.dao;

import java.util.List;

import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.exception.DaoException;

public interface RequestDao {
    List<Request> getRequestsForAdmin(int startElement) throws DaoException;

    List<Request> getRequestsForClient(int startElement) throws DaoException;
}
