package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.Room;

public interface CreateRoomDao {
    boolean createRoom(Room room) throws Exception;
}
