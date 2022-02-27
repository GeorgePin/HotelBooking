package com.epam.hotelbooking.dao;

import java.util.Optional;

import com.epam.hotelbooking.exception.DaoException;

public interface Dao<T> {

    boolean create(T item) throws DaoException;

    Optional<T> read(Long itemId) throws DaoException;

    boolean update(Long itemId, Object... params) throws DaoException;

    boolean delete(Long itemId) throws DaoException;
}