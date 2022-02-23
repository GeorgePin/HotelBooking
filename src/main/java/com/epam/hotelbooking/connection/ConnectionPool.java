package com.epam.hotelbooking.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static ConnectionPool instance;
    private List<ProxyConnection> availableConnections;
    private List<ProxyConnection> connectionInUse;
    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private static final int INITIAL_POOL_SIZE = 10;
    private static final Lock CONNECTIONS_LOCK = new ReentrantLock();

    private ConnectionPool() throws SQLException, IOException, ClassNotFoundException {
        availableConnections = new ArrayList<>();
        connectionInUse = new ArrayList<>();
    }

    public static ConnectionPool getInstance() throws SQLException, IOException, ClassNotFoundException {
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

    private void init() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "root");
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
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
