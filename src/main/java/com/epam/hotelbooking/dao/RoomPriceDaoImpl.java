package com.epam.hotelbooking.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RoomPriceRowMapper;

public class RoomPriceDaoImpl extends AbstractDao<RoomPrice> implements RoomPriceDao {
    private static final String GET_ALL_PRICES = "select * from room_price";

    public RoomPriceDaoImpl(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    @Override
    public List<RoomPrice> getRoomPrices() throws SQLException, DaoException {
        List<RoomPrice> roomPrices = executeQuery(GET_ALL_PRICES, new RoomPriceRowMapper());
        return roomPrices;
    }

    @Override
    public Optional<RoomPrice> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<RoomPrice> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(RoomPrice t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(RoomPrice t, String[] params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(RoomPrice t) {
        // TODO Auto-generated method stub

    }
}