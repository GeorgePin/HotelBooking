package com.epam.hotelbooking.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.entity.EntityType;
import com.epam.hotelbooking.entity.Identifable;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RowMapper;

public abstract class AbstractDao<T extends Identifable & Serializable> implements Dao<T> {
    private static final int RECORDS_PER_PAGE = 5;

    private final Connection connection;
    private final RowMapper<T> rowMapper;

    protected AbstractDao(Connection connection, RowMapper<T> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    protected List<T> executeQuery(String query, Object... params) throws SQLException {
        try (PreparedStatement statement = createStatement(query, params);
                ResultSet resultSet = statement.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = rowMapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;

    }

    protected Optional<T> executeForSingleResult(String query, Object... params) throws SQLException {
        List<T> entity = executeQuery(query, rowMapper, params);
        if (entity.size() == 1) {
            return Optional.of(entity.get(0));
        } else if (entity.size() > 1) {
            throw new IllegalArgumentException("More than one record was found");
        } else {
            return Optional.empty();
        }
    }

    protected boolean executeQueryWithoutReturnValue(String query, Object... params) throws SQLException {
        try (PreparedStatement statement = createStatement(query, params)) {
            return statement.execute();
        }
    }

    protected List<T> getItemsForPage(int startElement, EntityType entityType, String filter, String filterValue)
            throws SQLException {
        final String query = "select * from " + entityType.getEntityType() + " where " + filter + "=?" + filterValue
                + " limit ?, ?";
        return executeQuery(query, filterValue, startElement, RECORDS_PER_PAGE);
    }

    protected int getAmountOfItems(EntityType entityType, String filter, String filterValue) throws SQLException {
        final String query = "select count(*) from " + entityType.getEntityType() + " where " + filter + "="
                + filterValue;
        try (PreparedStatement statement = createStatement(query, filter, filterValue);
                ResultSet resultSet = statement.executeQuery()) {
            return resultSet.getInt(1);
        }
    }
}