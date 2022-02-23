package com.epam.hotelbooking.service;

import com.epam.hotelbooking.entity.Room;

public interface CreateRoomService  {
    boolean createRoom(Room room) throws Exception;
}
