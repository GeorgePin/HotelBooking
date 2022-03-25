package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.ItemsDto;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.exception.DaoException;

/**
 * This interface declares the methods that will interact with database.
 * 
 * @author George Papkevich
 * @version 1.0
 * @since 1.0
 */
public interface RequestDao extends Dao<Request>{

    /**
     * Purpose of this method is to get all clients from database.
     * 
     * @param pageNumber number of current page for which we need to get clients
     *                   from database.
     * @return {ItemsTransferObject} object which contains total number of pages and
     *         clients for specific page.
     * @throws DaoException
     */
    void insertRoomIntoRequest(Long requestId, Long roomId) throws DaoException;

    /**
     * Purpose of this method is to get all unapproved requests from database.
     * 
     * @param pageNumber number of current page for which we need to get requests
     *                   from database.
     * @return {ItemsTransferObject} object which contains total number of pages and
     *         requests for specific page.
     * @throws DaoException
     */
    ItemsDto<Request> getUnapprovedRequestsForAdmin(int pageNumber) throws DaoException;

    /**
     * Purpose of this method is to get all requests for specific client from
     * database.
     * 
     * @param pageNumber number of current page for which we need to get requests
     *                   from database.
     * @param userId     id of user which requests will be returned from database.
     * @return {ItemsTransferObject} object which contains total number of pages and
     *         requests for specific page.
     * @throws DaoException
     */
    ItemsDto<Request> getRequestsForClient(int pageNumber, Long userId) throws DaoException;
}
