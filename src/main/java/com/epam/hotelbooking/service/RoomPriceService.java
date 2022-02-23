package com.epam.hotelbooking.service;

import java.util.List;

import com.epam.hotelbooking.entity.RoomPrice;

public interface RoomPriceService {
    List<RoomPrice> getRoomPrices() throws Exception;
}
