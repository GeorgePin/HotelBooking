package com.epam.hotelbooking.dao;

import java.sql.SQLException;

public interface DeleteRoomDao {
    boolean deleteRoom(Long roomId) throws SQLException;
}
