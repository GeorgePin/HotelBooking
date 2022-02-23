package com.epam.hotelbooking.dao;

public interface HandleRequestDao {
    public boolean handleRoomRequest(Long requestId, Long roomId) throws Exception;
}
