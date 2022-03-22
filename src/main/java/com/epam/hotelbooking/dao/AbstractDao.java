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

/**
 * An abstract class from which any dao class should be extended from.
 * 
 * @author George Papkevich
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractDao<T extends Entity> implements Dao<T> {

    /**
     * Amount of items per single page.
     */
    protected static final int RECORDS_PER_PAGE = 5;
    protected static final String ZERO = "0";
    private final Connection connection;
    private final RowMapper<T> rowMapper;

    protected AbstractDao(Connection connection, RowMapper<T> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    /**
     * Purpose of this method is to build statement with the help of
     * {@link #createStatement()} and execute it with given {@code params}.
     * 
     * @param query  query which will be executed.
     * @param params parameters of query.
     * @return entities list of specific entities.
     * @throws DaoException
     */
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

    /**
     * Purpose of this method is to help {@link #executeQuery()} build statement.
     * 
     * @param query  query which will be executed.
     * @param params parameters of query.
     * @return statement statement for execution.
     * @throws DaoException
     */
    private PreparedStatement createStatement(String query, Object... params) throws DaoException {
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

    /**
     * Doing the same as {@link #executeQuery()} but should return only one entity,
     * otherwise throws {@code IllegalArgumentException} if found entities are more
     * than one, or returns empty {@code Optional}.
     * 
     * @param query  query which will be executed.
     * @param params parameters of query.
     * @return {@code Optional<T>} single entity.
     * @throws DaoException
     * @throws IllegalArgumentException
     */
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

    /**
     * Doing the same as {@link #executeQuery()} but should not return anything.
     * 
     * @param query  query which will be executed.
     * @param params parameters of query.
     * @throws DaoException
     */
    protected final void executeQueryWithoutReturnValue(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.execute();
        } catch (SQLException exception) {
            throw new DaoException("Error during executing query without return value", exception);
        }
    }

    /**
     * Method which build up a query from given filter , entity type and filter
     * value and returns a number of pages for.
     * 
     * @param entityType  type of entity which will be inserted in query.
     * @param filter      specific condition.
     * @param filterValue value of condition.
     * @return {@code Integer} amount of pages.
     * @throws DaoException
     */
    protected final Integer getAmountOfPages(EntityType entityType, String filter, String filterValue)
            throws DaoException {
        final String query = "select count(*) from " + entityType.getEntityType() + " where " + filter + "="
                + filterValue;
        return getAmountOfPagesExecutor(query);
    }

    /**
     * Method which build up a query from given entity type.
     * 
     * @param entityType type of entity which will be inserted in query.
     * 
     * @return {@code Integer} amount of pages.
     * @throws DaoException
     */
    protected final Integer getAmountOfPages(EntityType entityType) throws DaoException {
        final String query = "select count(*) from " + entityType.getEntityType();
        return getAmountOfPagesExecutor(query);
    }

    /**
     * This method build up statement from given query, executes it and after
     * calculates number of pages by dividing number of items on
     * {@value #RECORDS_PER_PAGE}.
     * 
     * @param query query for execution.
     * @return {@code Integer} amount of pages.
     * @throws DaoException
     */
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