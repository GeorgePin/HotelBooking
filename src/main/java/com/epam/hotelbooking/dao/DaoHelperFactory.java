package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.connection.ConnectionPool;

public class DaoHelperFactory {

    public DaoHelper create() throws Exception {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
