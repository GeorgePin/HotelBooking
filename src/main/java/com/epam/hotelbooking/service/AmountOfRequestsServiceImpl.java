package com.epam.hotelbooking.service;

import java.util.Optional;

import com.epam.hotelbooking.dao.AmountOfRequestsDao;
import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.entity.RequestsAmount;

public class AmountOfRequestsServiceImpl implements AmountOfRequestsService {

    private DaoHelperFactory daoHelperFactory;

    public AmountOfRequestsServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public Optional<RequestsAmount> getNumberOfRequests() throws Exception {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            AmountOfRequestsDao dao = helper.createAmountOfRequestsDao();
            Optional<RequestsAmount> amountOfRequests = dao.getNumberOfRequests();
            helper.endTransaction();
            return amountOfRequests;
        }
    }
}
