package com.epam.hotelbooking.service;

import java.io.IOException;
import java.sql.SQLException;

public interface DeleteRoomService {
    boolean deleteRoom(Long roomId) throws ClassNotFoundException, SQLException, IOException, Exception;
}
