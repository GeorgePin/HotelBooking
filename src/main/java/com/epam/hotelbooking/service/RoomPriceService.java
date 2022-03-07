package com.epam.hotelbooking.service;

import java.util.List;

import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.ServiceException;

public interface RoomPriceService {

    List<RoomPrice> getRoomPrices() throws ServiceException;
}
