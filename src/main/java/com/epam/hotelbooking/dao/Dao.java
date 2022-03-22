package com.epam.hotelbooking.dao;

import java.util.Optional;

import com.epam.hotelbooking.entity.Entity;
import com.epam.hotelbooking.exception.DaoException;

/**
 * This interface declares the CRUD methods that will interact with database.
 * 
 * @author George Papkevich
 * @version 1.0
 * @since 1.0
 */
public interface Dao<T extends Entity> {
    static final String NO_IMPLEMENTATION = "No implementation";

    /**
     * Purpose of this method is to create specific item on database.
     * 
     * @param item specific item which will be inserted into database.
     * @return {@code true} if item was created successfully.
     * @throws DaoException
     */
    boolean create(T item) throws DaoException;

    /**
     * Purpose of this method is to read specific item from database.
     * 
     * @param itemId id of item which will be read from database.
     * @return {@code Optional<T>} item from database.
     * @throws DaoException
     */
    Optional<T> read(Long itemId) throws DaoException;

    /**
     * Purpose of this method is to update specific item in database.
     * 
     * @param query  query which will be executed to update item.
     * @param params parameters which will be inserted into query
     * @throws DaoException
     */
    void update(String query, Object... params) throws DaoException;

    /**
     * Purpose of this method is to delete specific item from database.
     * 
     * @param itemId if of item which will be deleted.
     * @throws DaoException
     */
    default void delete(Long itemId) throws DaoException {
        throw new UnsupportedOperationException(NO_IMPLEMENTATION);
    }
}