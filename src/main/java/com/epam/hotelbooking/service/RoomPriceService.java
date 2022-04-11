package com.epam.hotelbooking.service;

import java.util.List;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.ServiceException;

/**
 * This interface declares the methods that will interact with Command and Dao
 * layer. Methods interacts with {@code RoomPrice}.
 * 
 * @author George Papkevich
 * @version 1.0
 * @since 1.0
 */
public interface RoomPriceService {

    /**
     * This method returns list of {@code RoomPrice}.
     * 
     * @return {@code List<RoomPrice>} list of room prices.
     * @throws ServiceException
     */
    List<RoomPrice> getRoomPrices() throws ServiceException;
}
