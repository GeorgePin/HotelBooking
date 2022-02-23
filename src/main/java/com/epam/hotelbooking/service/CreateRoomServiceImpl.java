package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.CreateRoomDao;
import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.entity.Room;

public class CreateRoomServiceImpl implements CreateRoomService {

    private DaoHelperFactory daoHelperFactory;

    public CreateRoomServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public boolean createRoom(Room room) throws Exception {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CreateRoomDao dao = helper.createCreateRoomDao();
            boolean isRoomCreated = dao.createRoom(room);
            helper.endTransaction();
            return isRoomCreated;
        }
    }
}
