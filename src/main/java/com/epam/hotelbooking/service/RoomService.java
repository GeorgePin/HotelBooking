package com.epam.hotelbooking.service;

import java.util.Optional;

import com.epam.hotelbooking.entity.ItemsDto;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.ServiceException;

/**
 * This interface declares the methods that will interact with Command and Dao
 * layer. Methods interacts with {@code Room}.
 * 
 * @author George Papkevich
 * @version 1.0
 * @since 1.0
 */
public interface RoomService {

    /**
     * This method transfers {@code Room} to Dao layer so that the room can be
     * created in database later.
     * 
     * @param room room which will be created in database.
     * @return
     * @throws ServiceException
     */
    boolean createRoom(Room room) throws ServiceException;

    /**
     * This method transfers transfers room id to Dao layer which should delete room
     * from database.
     * 
     * @param roomId id of room which will be deleted from database.
     * @throws ServiceException
     */
    void deleteRoom(Long roomId) throws ServiceException;

    /**
     * Purpose of this method is to get specific amount of {@code Room} depending on
     * {@code pageNumber}. This method returns {@code ItemsTransferObject} which
     * contains list of {@code Room} and total amount of pages.
     * 
     * @param pageNumber   number of current page.
     * @param roomCapacity given capacity by which rooms will be selected later.
     * @return {@code ItemsTransferObject} data transfer object which contains list
     *         of {@code Room} and amount of pages.
     * @throws ServiceException
     */
    ItemsDto<Room> getRoomsForRequestHandling(int pageNumber, Integer roomCapacity) throws ServiceException;

    /**
     * Purpose of this method is to get specific amount of {@code Room} depending on
     * {@code pageNumber}. This method returns {@code ItemsTransferObject} which
     * contains list of {@code Room} and total amount of pages.
     * 
     * @param pageNumber number of current page.
     * @return {@code ItemsTransferObject} data transfer object which contains list
     *         of {@code Room} and amount of pages.
     * @throws ServiceException
     */
    ItemsDto<Room> getRoomsForView(int pageNumber) throws ServiceException;

    /**
     * This method transfers transfers room id to Dao layer which should unblock
     * room in database.
     * 
     * @param roomId id of room which will be unblocked in database.
     * @throws ServiceException
     */
    void setStateOfRoom(Long roomId, boolean state) throws ServiceException;

    /**
     * This method transfers transfers room id to Dao layer which should return room
     * from database.
     * 
     * @param roomId id of room which will be returned from database.
     * @return {@code Optional<Room>} room from database.
     * @throws ServiceException
     */
    Optional<Room> readRoom(Long roomId) throws ServiceException;

    /**
     * This method transfers transfers {@code Room} to Dao layer which should update
     * existing room in database.
     * 
     * @param room entity which will be inserted in database.
     * @return {@code true} if room was updated successfully.
     * @throws ServiceException
     */
    boolean editRoom(Room room) throws ServiceException;
}
