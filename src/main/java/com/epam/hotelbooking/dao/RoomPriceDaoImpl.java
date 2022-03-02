package com.epam.hotelbooking.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RoomPriceRowMapper;
import com.epam.hotelbooking.mapper.RowMapper;

public class RoomPriceDaoImpl extends AbstractDao<RoomPrice> implements RoomPriceDao {
    private static final String GET_ALL_PRICES = "select * from room_price";

    public RoomPriceDaoImpl(ProxyConnection proxyConnection, RowMapper<RoomPrice> rowMapper) {
        super(proxyConnection, rowMapper);
    }

    @Override
    public boolean create(RoomPrice item) throws DaoException {
        throw new UnsupportedOperationException(NO_IMPLEMENTATION);
    }

    @Override
    public Optional<RoomPrice> read(Long itemId) throws DaoException {
        throw new UnsupportedOperationException(NO_IMPLEMENTATION);
    }

    @Override
    public boolean update(Long itemId, String query, Object... params) throws DaoException {
        throw new UnsupportedOperationException(NO_IMPLEMENTATION);
    }

    @Override
    public boolean delete(Long itemId) throws DaoException {
        throw new UnsupportedOperationException(NO_IMPLEMENTATION);
    }

    @Override
    public List<RoomPrice> getRoomsPrices() throws DaoException {
        List<RoomPrice> roomPrices;
        try {
            roomPrices = executeQuery(GET_ALL_PRICES, new RoomPriceRowMapper());
        } catch (SQLException exception) {
            throw new DaoException("Exception during getting room prices", exception);
        }
        return roomPrices;
    }
}