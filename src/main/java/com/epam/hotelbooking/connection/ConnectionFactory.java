package com.epam.hotelbooking.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.epam.hotelbooking.exception.DaoException;

public class ConnectionFactory {
    private static final String DB_SETTINGS_PROPERTY_FILE = "connection.properties";

    public Connection createConnection() throws DaoException {
        Properties props = new Properties();
        try {
            props.load((this.getClass()
                    .getClassLoader()
                    .getResourceAsStream(DB_SETTINGS_PROPERTY_FILE)));
            String dbDriverClass = props.getProperty("db.driver.class");
            String dbConnUrl = props.getProperty("db.conn.url");
            String dbUserName = props.getProperty("db.username");
            String dbPassword = props.getProperty("db.password");
            Class.forName(dbDriverClass);
            return DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
        } catch (IOException | ClassNotFoundException | SQLException exception) {
            throw new DaoException("Error during connection pool initialization", exception);
        }
    }

    public ProxyConnection createProxyConnection(Connection connection) throws DaoException {
        return new ProxyConnection(connection, ConnectionPool.getInstance());
    }
}
