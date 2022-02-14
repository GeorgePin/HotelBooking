package com.epam.hotelbooking.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.epam.hotelbooking.connection.ConnectionPool;

public class DaoHelperFactory {

    public DaoHelper create() throws SQLException, IOException, ClassNotFoundException {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
