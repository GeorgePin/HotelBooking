package com.epam.hotelbooking.dao;

import java.sql.SQLException;

import com.epam.hotelbooking.connection.ConnectionPool;
import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RowMapper;

public class DaoHelper implements AutoCloseable {

    private ProxyConnection connection;

    public DaoHelper() throws DaoException {
        this.connection = ConnectionPool.getInstance()
                .getConnection();
    }

    public UserDao createUserDao(RowMapper<User> rowMapper) {
        return new UserDaoImpl(connection, rowMapper);
    }

    public RoomPriceDao createRoomPriceDao(RowMapper<RoomPrice> rowMapper) {
        return new RoomPriceDaoImpl(connection, rowMapper);
    }

    public RequestDao createRequestDao(RowMapper<Request> rowMapper) {
        return new RequestDaoImpl(connection, rowMapper);
    }

    public RoomDao createRoomDao(RowMapper<Room> rowMapper) {
        return new RoomDaoImpl(connection, rowMapper);
    }

    @Override
    public void close() throws DaoException {
        try {
            connection.close();
        } catch (SQLException exception) {
            throw new DaoException("Error during releasing connection back to connection pool", exception);
        }
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException exception) {
            throw new DaoException("Error during starting transaction", exception);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException exception) {
            throw new DaoException("Error during ending transaction", exception);
        }
    }
}
