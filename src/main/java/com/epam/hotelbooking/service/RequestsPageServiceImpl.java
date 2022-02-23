package com.epam.hotelbooking.service;

import java.util.List;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.dao.RequestsPageDao;
import com.epam.hotelbooking.entity.Request;

public class RequestsPageServiceImpl implements RequestsPageService {
    private DaoHelperFactory daoHelperFactory;

    public RequestsPageServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<Request> getRequests(int startElement) throws Exception {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            RequestsPageDao dao = helper.createRequestsPageDao();
            List<Request> requests = dao.getRequests(startElement);
            helper.endTransaction();
            return requests;
        }
    }

}
