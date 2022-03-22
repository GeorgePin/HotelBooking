package com.epam.hotelbooking.dao;

import java.util.List;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;

/**
 * This interface declares the methods that will interact with database.
 * 
 * @author George Papkevich
 * @version 1.0
 * @since 1.0
 */
public interface RoomPriceDao {

    /**
     * Purpose of this method is to get all room prices from database.
     * 
     * @return {@code List<RoomPrice>} list of all prices.
     * @throws DaoException
     */
    List<RoomPrice> getRoomsPrices() throws DaoException;
}
