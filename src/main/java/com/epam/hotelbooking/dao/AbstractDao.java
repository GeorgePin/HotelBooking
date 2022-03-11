package com.epam.hotelbooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.entity.Entity;
import com.epam.hotelbooking.entity.EntityType;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RowMapper;

public abstract class AbstractDao<T extends Entity> implements Dao<T> {
    protected static final int RECORDS_PER_PAGE = 5;
    protected static final String NO_IMPLEMENTATION = "No implementation";
    protected static final String ZERO = "0";
    private final Connection connection;
    private final RowMapper<T> rowMapper;

    protected AbstractDao(Connection connection, RowMapper<T> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    protected final List<T> executeQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params);
                ResultSet resultSet = statement.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = rowMapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException exception) {
            throw new DaoException("Error during executing query", exception);
        }
    }

    private final PreparedStatement createStatement(String query, Object... params) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 1; i <= params.length; i++) {
                statement.setObject(i, params[i - 1]);
            }
            return statement;
        } catch (SQLException exception) {
            throw new DaoException("Error during creating statement", exception);
        }
    }

    protected final Optional<T> executeForSingleResult(String query, Object... params) throws DaoException {
        List<T> entity = executeQuery(query, params);
        if (entity.size() == 1) {
            return Optional.of(entity.get(0));
        } else if (entity.size() > 1) {
            throw new IllegalArgumentException("More than one record was found");
        } else {
            return Optional.empty();
        }
    }

    protected final void executeQueryWithoutReturnValue(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.execute();
        } catch (SQLException exception) {
            throw new DaoException("Error during executing query without return value", exception);
        }
    }

    protected final Integer getAmountOfPages(EntityType entityType, String filter, String filterValue)
            throws DaoException {
        final String query = "select count(*) from " + entityType.getEntityType() + " where " + filter + "="
                + filterValue;
        return getAmountOfPagesExecutor(query);
    }

    protected final Integer getAmountOfPages(EntityType entityType) throws DaoException {
        final String query = "select count(*) from " + entityType.getEntityType();
        return getAmountOfPagesExecutor(query);
    }

    private Integer getAmountOfPagesExecutor(String query) throws DaoException {
        try (PreparedStatement statement = createStatement(query); ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                Integer amountOfItems = resultSet.getInt(1);
                int numberOfPages = (int) Math.ceil(amountOfItems * 1.0 / RECORDS_PER_PAGE);
                return numberOfPages;
            } else {
                throw new DaoException("Can't find amountOfItems from resultSet");
            }
        } catch (SQLException exception) {
            throw new DaoException("Error during getting amount of all items", exception);
        }
    }
}