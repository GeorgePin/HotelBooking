package com.epam.hotelbooking.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class ConnectionFactory {
    final Properties properties = new Properties();

    public Queue<ProxyConnection> createPool(int poolSize) throws SQLException, IOException, ClassNotFoundException {
        Queue<ProxyConnection> proxyConnections = new ArrayDeque<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            proxyConnections.add(createProxyConnection());
        }
        return proxyConnections;
    }

//    public static void main(String... args) throws InterruptedException {
//        File file = new File("src/main/resources/connection");
//        System.out.println(file.is
//        System.out.println(file.canRead());
//        TimeUnit.MILLISECONDS.wait(100);
//    }

    private ProxyConnection createProxyConnection() throws SQLException, IOException, ClassNotFoundException {
//        try (FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/connection"))) {
//            properties.load(fileInputStream);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "root");
        return new ProxyConnection(connection);
//        }
    }
}