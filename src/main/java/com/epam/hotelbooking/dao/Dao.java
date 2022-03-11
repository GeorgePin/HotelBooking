package com.epam.hotelbooking.dao;

import java.util.Optional;

import com.epam.hotelbooking.entity.Entity;
import com.epam.hotelbooking.exception.DaoException;

public interface Dao<T extends Entity> {

    void create(T item) throws DaoException;

    Optional<T> read(Long itemId) throws DaoException;

    void update(String query, Object... params) throws DaoException;

    void delete(Long itemId) throws DaoException;
}