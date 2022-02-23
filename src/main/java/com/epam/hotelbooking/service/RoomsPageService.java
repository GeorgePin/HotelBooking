package com.epam.hotelbooking.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.DaoException;

public interface RoomsPageService {
    List<Room> getRooms(int startElement) throws DaoException, ClassNotFoundException, SQLException, IOException, Exception;
}
