package com.epam.hotelbooking.dao;

import java.sql.SQLException;
import java.util.List;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.DaoException;

public interface RoomsPageDao {
    List<Room> getRooms(int startElement) throws DaoException, SQLException;
}
