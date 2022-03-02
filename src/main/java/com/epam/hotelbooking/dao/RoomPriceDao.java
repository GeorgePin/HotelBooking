package com.epam.hotelbooking.dao;

import java.util.List;

import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;

public interface RoomPriceDao {
    List<RoomPrice> getRoomsPrices() throws DaoException;
}
