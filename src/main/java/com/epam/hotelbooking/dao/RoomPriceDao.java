package com.epam.hotelbooking.dao;

import java.sql.SQLException;
import java.util.List;

import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;

public interface RoomPriceDao {
 List<RoomPrice> getRoomPrices() throws SQLException, DaoException;
}
