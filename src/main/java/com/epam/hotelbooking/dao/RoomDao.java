package com.epam.hotelbooking.dao;

import java.util.List;

import com.epam.hotelbooking.entity.Room;

public interface RoomDao {
    List<Room> getFreeRooms() throws Exception;
}
