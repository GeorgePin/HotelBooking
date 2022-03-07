package com.epam.hotelbooking.dao;

import java.util.List;

import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.exception.DaoException;

public interface RequestDao {

    List<Request> getRequestsForClient(int startElement) throws DaoException;

    void insertRoomIntoRequest(Long requestId, Long roomId) throws DaoException;

    List<Request> getUnapprovedRequestsForAdmin(int startElement) throws DaoException;

    Integer getAmountOfPages() throws DaoException;
}
