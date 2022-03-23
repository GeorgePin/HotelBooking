package com.epam.hotelbooking.dao;

import java.util.Optional;

import com.epam.hotelbooking.entity.ItemsDto;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;

/**
 * This interface declares the methods that will interact with database.
 * 
 * @author George Papkevich
 * @version 1.0
 * @since 1.0
 */
public interface UserDao extends Dao<User>{

    /**
     * Purpose of this method is to get specific user from database, knowing login
     * and password.
     * 
     * @param login    user's login.
     * @param password user's password.
     * @return {@code Optional<User>} user which was found by login and password.
     * @throws DaoException
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Purpose of this method is to block specific user in database, knowing
     * user's id.
     * 
     * @param userId id of user which will be banned.
     * @throws DaoException
     */
    void banUser(Long userId) throws DaoException;

    /**
     * Purpose of this method is to unblock specific user from database, knowing
     * user's id.
     * 
     * @param userId id of user which will be unbanned.
     * @throws DaoException
     */
    void unbanUser(Long userId) throws DaoException;

    /**
     * Purpose of this method is to get all clients from database.
     * 
     * @param pageNumber number of current page for which we need to get clients
     *                   from database.
     * @return {ItemsTransferObject} object which contains total number of pages and
     *         clients for specific page.
     * @throws DaoException
     */
    ItemsDto getAllClients(int pageNumber) throws DaoException;

}
