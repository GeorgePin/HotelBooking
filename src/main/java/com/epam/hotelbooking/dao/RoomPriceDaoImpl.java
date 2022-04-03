package com.epam.hotelbooking.dao;

import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RowMapper;

public class RoomPriceDaoImpl extends AbstractDao<RoomPrice> implements RoomPriceDao {

    private static final String GET_ALL_PRICES = "select * from room_price";

    public RoomPriceDaoImpl(ProxyConnection proxyConnection, RowMapper<RoomPrice> rowMapper) {
        super(proxyConnection, rowMapper);
    }

    @Override
    public boolean create(RoomPrice item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<RoomPrice> read(Long itemId) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(String query, Object... params) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<RoomPrice> getRoomsPrices() throws DaoException {
        List<RoomPrice> roomPrices = executeQuery(GET_ALL_PRICES);
        return roomPrices;
    }
}