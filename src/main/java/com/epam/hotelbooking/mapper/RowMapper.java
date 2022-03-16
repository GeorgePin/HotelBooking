package com.epam.hotelbooking.mapper;

import java.sql.ResultSet;
import com.epam.hotelbooking.entity.Entity;
import com.epam.hotelbooking.exception.DaoException;

/**
 * Mapper for parsing resultSet.
 * 
 * @author George Papkevich
 * @version 1.0
 * @since 1.0
 */

public interface RowMapper<T extends Entity> {

    /**
     * This method receives {@code ResultSet} and parses into {@code Entity}
     * 
     * @param resultSet result set which will be parsed.
     * @throws DaoException if any exception occurred during execution.
     * 
     */
    T map(ResultSet resultSet) throws DaoException;
}
