package com.epam.hotelbooking.service;

import java.util.Optional;

import com.epam.hotelbooking.dao.AmountOfRoomsDao;
import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.entity.RoomsAmount;

public class AmountOfRoomsServiceImpl implements AmountOfRoomsService {

    private DaoHelperFactory daoHelperFactory;

    public AmountOfRoomsServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public Optional<RoomsAmount> getNumberOfRooms() throws Exception {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            AmountOfRoomsDao dao = helper.createAmountOfRoomsDao();
            Optional<RoomsAmount> amountOfRequests = dao.getNumberOfRooms();
            helper.endTransaction();
            return amountOfRequests;
        }
    }
}