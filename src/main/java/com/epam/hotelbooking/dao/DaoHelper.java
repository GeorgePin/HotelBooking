package com.epam.hotelbooking.dao;

import java.sql.SQLException;

import com.epam.hotelbooking.connection.ConnectionPool;
import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.exception.DaoException;

public class DaoHelper implements AutoCloseable {

    private ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) {
        this.connection = pool.getConnection();
    }

    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }

    public RequestRoomDao createRequestRoomDao() {
        return new RequestRoomDaoImpl(connection);
    }

    public HandleRequestDao createHandleRequestDao() {
        return new HandleRequestDaoImpl(connection);
    }

    public RequestsPageDao createRequestsPageDao() {
        return new RequestsPageDaoImpl(connection);
    }

    public AmountOfRequestsDao createAmountOfRequestsDao() {
        return new AmountOfRequestsDaoImpl(connection);
    }

    public RoomsPageDao createRoomsPageDao() {
        return new RoomsPageDaoImpl(connection);
    }

    public AmountOfRoomsDao createAmountOfRoomsDao() {
        return new AmountOfRoomsDaoImpl(connection);
    }

    public DeleteRoomDao createDeleteRoomDao() {
        return new DeleteRoomDaoImpl(connection);
    }

    public RoomPriceDao createRoomPriceDao() {
        return new RoomPriceDaoImpl(connection);
    }

    public CreateRoomDao createCreateRoomDao() {
        return new CreateRoomDaoImpl(connection);
    }

    public RequestDao createRequestDao() {
        return new RequestDaoImpl(connection);
    }

    public RoomDao createRoomDao() {
        return new RoomDaoImpl(connection);
    }

    @Override
    public void close() throws Exception {
        connection.getConnectionPool()
                .releaseConnection(connection);
        connection.close();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException exception) {
            throw new DaoException();
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException exception) {
            throw new DaoException();
        }
    }
}
