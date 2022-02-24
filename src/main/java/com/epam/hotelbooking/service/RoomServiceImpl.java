package com.epam.hotelbooking.service;

import java.util.List;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.dao.RoomDao;
import com.epam.hotelbooking.entity.Room;

public class RoomServiceImpl implements RoomService {
    private DaoHelperFactory daoHelperFactory;

    public RoomServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<Room> getAllFreeRooms() throws Exception {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            RoomDao dao = helper.createRoomDao();
            List<Room> freeRooms = dao.getFreeRooms();
            helper.endTransaction();
            return freeRooms;
        }
    }

}
