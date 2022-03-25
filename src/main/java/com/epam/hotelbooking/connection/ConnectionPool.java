package com.epam.hotelbooking.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.epam.hotelbooking.exception.DaoException;

public class ConnectionPool {

    private static ConnectionPool instance;
    private static final String DB_SETTINGS_PROPERTY_FILE = "connection.properties";
    private List<ProxyConnection> availableConnections;
    private List<ProxyConnection> connectionInUse;
    private static final Lock CONNECTIONS_LOCK = new ReentrantLock();
    private static final Lock GET_CONNECTION_LOCK = new ReentrantLock();
    private static final Lock RELEASE_CONNETION_LOCK = new ReentrantLock();

    private ConnectionPool() {
        availableConnections = new ArrayList<>();
        connectionInUse = new ArrayList<>();
    }

    public static ConnectionPool getInstance() throws DaoException {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            CONNECTIONS_LOCK.lock();
            localInstance = instance;
            try {
                if (localInstance == null) {
                    localInstance = new ConnectionPool();
                    instance = localInstance;
                    instance.init();
                }
            } finally {
                CONNECTIONS_LOCK.unlock();
            }
        }
        return localInstance;
    }

    private void init() throws DaoException {
        Properties props = new Properties();
        try {
            props.load((this.getClass()
                    .getClassLoader()
                    .getResourceAsStream(DB_SETTINGS_PROPERTY_FILE)));

            String dbDriverClass = props.getProperty("db.driver.class");
            String dbConnUrl = props.getProperty("db.conn.url");
            String dbUserName = props.getProperty("db.username");
            String dbPassword = props.getProperty("db.password");
            int dbPoolSize = Integer.parseInt(props.getProperty("db.poolsize"));
            Class.forName(dbDriverClass);
            for (int i = 0; i < dbPoolSize; i++) {
                Connection connection = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
                availableConnections.add(new ProxyConnection(connection, ConnectionPool.getInstance()));
            }
        } catch (IOException | ClassNotFoundException | SQLException exception) {
            throw new DaoException("Error during connection pool initialization", exception);
        }
    }

    public ProxyConnection getConnection() {
        try {
            GET_CONNECTION_LOCK.lock();
            ProxyConnection connection = availableConnections.remove(availableConnections.size() - 1);
            connectionInUse.add(connection);
            return connection;
        } finally {
            GET_CONNECTION_LOCK.unlock();
        }
    }

    public boolean releaseConnection(ProxyConnection connection) {
        try {
            RELEASE_CONNETION_LOCK.lock();
            availableConnections.add(connection);
            return connectionInUse.remove(connection);
        } finally {
            RELEASE_CONNETION_LOCK.unlock();
        }
    }
}