package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.exception.DaoException;

public interface RoomDao {

    void blockRoom(Long roomId) throws DaoException;

    ItemsTransferObject getFreeRoomsForSinglePage(int startElement) throws DaoException;

    ItemsTransferObject getRoomsWithPrices(int startElement) throws DaoException;

    void update(String query, Object... params) throws DaoException;

    void unblockRoom(Long itemId) throws DaoException;
}
