package com.epam.hotelbooking.connection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static ConnectionPool instance;
    private Queue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> connectionInUse;
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    private static final int INITIAL_POOL_SIZE = 10;
    private static final Lock CONNECTIONS_LOCK = new ReentrantLock(true);

    private ConnectionPool() throws SQLException, IOException, ClassNotFoundException {
        availableConnections = connectionFactory.createPool(INITIAL_POOL_SIZE);
        connectionInUse = new ArrayDeque<>();
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
                }
            } finally {
                CONNECTIONS_LOCK.unlock();
            }
        }
        return localInstance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = availableConnections.poll();
        connectionInUse.add(connection);
        return connection;
    }

    public boolean releaseConnection(ProxyConnection connection) {
        availableConnections.add(connection);
        return connectionInUse.remove(connection);
    }
}
