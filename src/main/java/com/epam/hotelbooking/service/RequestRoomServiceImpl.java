package com.epam.hotelbooking.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.dao.RequestRoomDao;

public class RequestRoomServiceImpl implements RequestRoomService {
    private DaoHelperFactory daoHelperFactory;

    public RequestRoomServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public boolean createRoomRequest(int roomCapacity, String roomClass, Date startDate, Date endDate, Long userId)
            throws ClassNotFoundException, SQLException, IOException, Exception {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            RequestRoomDao dao = helper.createRequestRoomDao();
            boolean hasRoomRequestCreated = dao.createRoomRequest(roomCapacity, roomClass, startDate, endDate, userId);
            helper.endTransaction();
            return hasRoomRequestCreated;
        }
    }
}
