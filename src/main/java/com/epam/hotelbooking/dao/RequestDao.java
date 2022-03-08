package com.epam.hotelbooking.dao;

import java.util.List;

import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.exception.DaoException;

public interface RequestDao {

    void insertRoomIntoRequest(Long requestId, Long roomId) throws DaoException;

    List<Request> getUnapprovedRequestsForAdmin(int startElement) throws DaoException;

    List<Request> getRequestsForClient(int pageNumber, Long userId) throws DaoException;

    Integer getAmountOfPagesForClient(Long userId) throws DaoException;
}
