package com.epam.hotelbooking.service;

public interface RequestHandlingService {
    public boolean handleRoomRequest(Long requestId, Long roomId) throws Exception;
}
