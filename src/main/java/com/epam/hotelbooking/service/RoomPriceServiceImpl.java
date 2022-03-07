package com.epam.hotelbooking.service;

import java.util.List;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.RoomPriceDaoImpl;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.mapper.RoomPriceRowMapper;

public class RoomPriceServiceImpl implements RoomPriceService {

    @Override
    public List<RoomPrice> getRoomPrices() throws ServiceException {
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomPriceDaoImpl roomPirceDao = daoHelper.createRoomPriceDao(new RoomPriceRowMapper());
            List<RoomPrice> roomPrices = roomPirceDao.getRoomsPrices();
            daoHelper.endTransaction();
            return roomPrices;
        } catch (DaoException exception) {
            throw new ServiceException("Error during getting room prices", exception);
        }
    }
}
