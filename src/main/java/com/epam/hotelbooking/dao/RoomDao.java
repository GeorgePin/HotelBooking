package com.epam.hotelbooking.dao;

import java.util.List;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.DaoException;

public interface RoomDao {

    void blockRoom(Long roomId) throws DaoException;

    List<Room> getFreeRoomsForSinglePage(int startElement) throws DaoException;

    List<Room> getRoomsWithPrices(int startElement) throws DaoException;

    void update(String query, Object... params) throws DaoException;

    Integer getAmountOfPagesForFreeRooms() throws DaoException;

    Integer getAmountOfPagesForRooms() throws DaoException;

    void unblockRoom(Long itemId) throws DaoException;
}
