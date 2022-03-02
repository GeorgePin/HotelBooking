package com.epam.hotelbooking.dao;

import java.util.List;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.DaoException;

public interface RoomDao {
    List<Room> getFreeRooms() throws DaoException;

    boolean blockRoom(Long roomId) throws DaoException;
}
