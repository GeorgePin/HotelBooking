package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.exception.DaoException;

public interface RequestDao {

    void insertRoomIntoRequest(Long requestId, Long roomId) throws DaoException;

    ItemsTransferObject getUnapprovedRequestsForAdmin(int startElement) throws DaoException;

    ItemsTransferObject getRequestsForClient(int pageNumber, Long userId) throws DaoException;
}
