package com.epam.hotelbooking.service;

import java.util.Optional;

import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.exception.ServiceException;

public interface RequestService {

    ItemsTransferObject getRequestsForUser(int startElement, boolean isAdmin)
            throws ServiceException;

    Optional<Request> getRequest(Long requestId) throws ServiceException;

    void handleRoomRequest(Long requestId, Long roomId) throws ServiceException;

    void createRoomRequest(Request request) throws ServiceException;

}
