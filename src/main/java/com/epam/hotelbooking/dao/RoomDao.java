package com.epam.hotelbooking.dao;

import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.DaoException;

/**
 * This interface declares the methods that will interact with database.
 * 
 * @author George Papkevich
 * @version 1.0
 * @since 1.0
 */
public interface RoomDao {

    /**
     * Purpose of this method is to block specific room in database by knowing its
     * id.
     * 
     * @param roomId id of room which will be vlocked.
     * @throws DaoException
     */
    void blockRoom(Long roomId) throws DaoException;

    /**
     * Purpose of this method is to get all free rooms for single page from
     * database.
     * 
     * @param pageNumber number of current page for which we need to get rooms from
     *                   database.
     * @return {ItemsTransferObject} object which contains total number of pages and
     *         rooms for specific page.
     * @throws DaoException
     */
    ItemsTransferObject getFreeRoomsForSinglePage(int pageNumber) throws DaoException;

    /**
     * Purpose of this method is to get all rooms for single page from database.
     * 
     * @param pageNumber number of current page for which we need to get rooms from
     *                   database.
     * @return {ItemsTransferObject} object which contains total number of pages and
     *         rooms for specific page.
     * @throws DaoException
     */
    ItemsTransferObject getRoomsWithPrices(int pageNumber) throws DaoException;

    /**
     * Purpose of this method is to update specific room in database.
     * 
     * @param query  query which will be executed to update room.
     * @param params parameters which will be inserted into query
     * @throws DaoException
     */
    void update(String query, Object... params) throws DaoException;

    /**
     * Purpose of this method is to unblock room knowing its id.
     * 
     * @param itemId id of room which will be blocked.
     * @throws DaoException
     */
    void unblockRoom(Long itemId) throws DaoException;

    /**
     * Purpose of this method is to update room knowing its id.
     * 
     * @param itemId id of room which will be updated.
     * @return {@code true} if room was updated successfully.
     * @throws DaoException
     */
    boolean editRoom(Room room) throws DaoException;
}
