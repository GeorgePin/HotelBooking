package com.epam.hotelbooking.service;

import java.sql.Date;

public interface RequestRoomService {
    public boolean createRoomRequest(int roomCapacity, String roomClass, Date startDate, Date endDate, Long userId)
            throws Exception;
}
