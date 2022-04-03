package com.epam.hotelbooking.connection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.epam.hotelbooking.exception.DaoException;

public class ConnectionPool {

    private static final int POOL_SIZE = 10;
    private static final Lock INSTANCE_LOCK = new ReentrantLock();
    private static final Lock CONNECTION_LOCK = new ReentrantLock();
    private static ConnectionPool instance;
    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private List<ProxyConnection> availableConnections;
    private List<ProxyConnection> connectionInUse;

    private ConnectionPool() {
        availableConnections = new ArrayList<>();
        connectionInUse = new ArrayList<>();
    }

    public static ConnectionPool getInstance() throws DaoException {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            INSTANCE_LOCK.lock();
            localInstance = instance;
            try {
                if (localInstance == null) {
                    localInstance = new ConnectionPool();
                    instance = localInstance;
                    instance.init();
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return localInstance;
    }

    private void init() throws DaoException {
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = connectionFactory.createConnection();
            ProxyConnection proxyConnection = connectionFactory.createProxyConnection(connection);
            availableConnections.add(proxyConnection);
        }

    }

    public ProxyConnection getConnection() {
        try {
            CONNECTION_LOCK.lock();
            ProxyConnection connection = availableConnections.remove(availableConnections.size() - 1);
            connectionInUse.add(connection);
            return connection;
        } finally {
            CONNECTION_LOCK.unlock();
        }
    }

    public boolean releaseConnection(ProxyConnection connection) {
        try {
            CONNECTION_LOCK.lock();
            availableConnections.add(connection);
            return connectionInUse.remove(connection);
        } finally {
            CONNECTION_LOCK.unlock();
        }
    }
}