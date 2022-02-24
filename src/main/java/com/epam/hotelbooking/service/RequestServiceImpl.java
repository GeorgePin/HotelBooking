package com.epam.hotelbooking.service;

import java.util.Optional;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.dao.RequestDao;
import com.epam.hotelbooking.entity.Request;

public class RequestServiceImpl implements RequestService {
    private DaoHelperFactory daoHelperFactory;

    public RequestServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public Optional<Request> getRequest(Long requestId) throws Exception {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            RequestDao dao = helper.createRequestDao();
            Optional<Request> request = dao.getRequest(requestId);
            helper.endTransaction();
            return request;
        }
    }

}
