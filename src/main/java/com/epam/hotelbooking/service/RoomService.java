package com.epam.hotelbooking.service;

import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.ServiceException;

public interface RoomService {

    void createRoom(Room room) throws ServiceException;

    void deleteRoom(Long roomId) throws ServiceException;

    ItemsTransferObject getRoomsForSinglePage(int startElement, boolean isForHandling)
            throws ServiceException;
}
