package com.epam.hotelbooking.service;

import java.io.IOException;
import java.sql.SQLException;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.dao.DeleteRoomDao;

public class DeleteRoomServiceImpl implements DeleteRoomService {
    private DaoHelperFactory daoHelperFactory;

    public DeleteRoomServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public boolean deleteRoom(Long roomId) throws ClassNotFoundException, SQLException, IOException, Exception {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            DeleteRoomDao dao = helper.createDeleteRoomDao();
            boolean isRoomDeleted = dao.deleteRoom(roomId);
            helper.endTransaction();
            return isRoomDeleted;
        }
    }
}
