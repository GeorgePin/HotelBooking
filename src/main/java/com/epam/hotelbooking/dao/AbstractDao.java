package com.epam.hotelbooking.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.Identifable;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RowMapper;

public abstract class AbstractDao<T extends Identifable> implements Dao<T> {

    private final ProxyConnection proxyConnection;

    protected AbstractDao(ProxyConnection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }

    protected List<T> executeQuery(String query, RowMapper<T> mapper, Object... params)
            throws DaoException, SQLException {
        try (PreparedStatement statement = createStatement(query, params);
                ResultSet resultSet = statement.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = proxyConnection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    protected Optional<T> executeForSingleResult(String query, RowMapper<T> mapper, Object... params) throws Exception {
        List<T> entity = executeQuery(query, mapper, params);
        if (entity.size() == 1) {
            return Optional.of(entity.get(0));
        } else {
            throw new Exception("AbstractDaoException");
        }
    }
}