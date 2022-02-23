package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.dao.HandleRequestDao;

public class RequestHandlingServiceImpl implements RequestHandlingService {

    private DaoHelperFactory daoHelperFactory;

    public RequestHandlingServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public boolean handleRoomRequest(Long requestId, Long roomId) throws Exception {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            HandleRequestDao dao = helper.createHandleRequestDao();
            boolean hasRoomRequestCreated = dao.handleRoomRequest(requestId, roomId);
            helper.endTransaction();
            return hasRoomRequestCreated;
        }
    }
}
