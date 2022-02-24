package com.epam.hotelbooking.service;

import java.util.List;

import com.epam.hotelbooking.entity.Room;

public interface RoomService {
    List<Room> getAllFreeRooms() throws Exception;
}
