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

public class ConnectionPool {

    private static ConnectionPool instance;
    private static final String DB_SETTINGS_PROPERTY_FILE = "connection.properties";
    private List<ProxyConnection> availableConnections;
    private List<ProxyConnection> connectionInUse;
    private static final Lock CONNECTIONS_LOCK = new ReentrantLock();

    private ConnectionPool() throws SQLException, IOException, ClassNotFoundException {
        availableConnections = new ArrayList<>();
        connectionInUse = new ArrayList<>();
    }

    public static ConnectionPool getInstance() throws Exception {
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

    private void init() throws Exception {
        Properties props = new Properties();
        props.load((this.getClass()
                .getClassLoader()
                .getResourceAsStream(DB_SETTINGS_PROPERTY_FILE)));
        String dbDriverClass = props.getProperty("db.driver.class");
        String dbConnUrl = props.getProperty("db.conn.url");
        String dbUserName = props.getProperty("db.username");
        String dbPassword = props.getProperty("db.password");
        int dbPoolSize = Integer.parseInt(props.getProperty("db.poolsize"));
        Class.forName(dbDriverClass);
        Connection connection = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
        for (int i = 0; i < dbPoolSize; i++) {
            availableConnections.add(new ProxyConnection(connection, ConnectionPool.getInstance()));
        }
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = availableConnections.remove(availableConnections.size() - 1);
        connectionInUse.add(connection);
        return connection;
    }

    public boolean releaseConnection(ProxyConnection connection) throws SQLException {
        availableConnections.add(connection);
        return connectionInUse.remove(connection);
    }
}
