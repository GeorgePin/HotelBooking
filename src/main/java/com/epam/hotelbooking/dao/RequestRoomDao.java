package com.epam.hotelbooking.dao;

import java.sql.Date;
import java.sql.SQLException;

public interface RequestRoomDao {
    public boolean createRoomRequest(int roomCapacity, String roomClass, Date startDate, Date endDate, Long userId)
            throws SQLException;
}
