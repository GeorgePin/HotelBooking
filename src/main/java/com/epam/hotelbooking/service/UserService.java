package com.epam.hotelbooking.service;

import java.util.Optional;

import com.epam.hotelbooking.entity.ItemsDto;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.ServiceException;

/**
 * This interface declares the methods that will interact with Command and Dao
 * layer. Methods interacts with {@code User}.
 * 
 * @author George Papkevich
 * @version 1.0
 * @since 1.0
 */
public interface UserService {

    /**
     * 
     * Purpose of this method is to get specific amount of clients from database
     * depending on {@code pageNumber}. This method returns
     * {@code ItemsTransferObject} which contains list of {@code User} and total
     * amount of pages.
     * 
     * @param pageNumber number of current page.
     * @return {@code ItemsTransferObject} data transfer object which contains list
     *         of clients and amount of pages.
     * @throws ServiceException
     */
    ItemsDto<User> getAllClients(int pageNumber) throws ServiceException;

    /**
     * This method transfers data for logging in to Dao layer.
     * 
     * @param login    user's login.
     * @param password user's password.
     * @return {@code Optional<User>} user founded by login and password.
     * @throws ServiceException
     */
    Optional<User> login(String login, String password) throws ServiceException;

    /**
     * This method transfers transfers {@code User} id to Dao layer which should
     * block user in database.
     * 
     * @param user id of user which will be blocked in database.
     * @throws ServiceException
     */
    void setUserState(Long userId, boolean state) throws ServiceException;

    /**
     * This method transfers {@code User} to Dao layer so that the user can be
     * created in database later.
     * 
     * @param user user which will be created in database.
     * @return {@code true} if user was created successfully.
     * @throws ServiceException
     */
    boolean createUser(User user) throws ServiceException;

}
