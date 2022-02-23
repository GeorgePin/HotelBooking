package com.epam.hotelbooking.service;

import java.util.List;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.dao.RoomPriceDao;
import com.epam.hotelbooking.entity.RoomPrice;

public class RoomPriceServiceImpl implements RoomPriceService {
    private DaoHelperFactory daoHelperFactory;

    public RoomPriceServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<RoomPrice> getRoomPrices() throws Exception {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            RoomPriceDao dao = helper.createRoomPriceDao();
            List<RoomPrice> roomPrices = dao.getRoomPrices();
            helper.endTransaction();
            return roomPrices;
        }
    }

}
