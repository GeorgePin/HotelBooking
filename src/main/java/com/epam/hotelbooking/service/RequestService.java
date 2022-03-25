package com.epam.hotelbooking.service;

import java.util.Optional;
import com.epam.hotelbooking.entity.ItemsDto;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.exception.ServiceException;

/**
 * This interface declares the methods that will interact with Command and Dao
 * layer. Methods interacts with {@code Request}.
 * 
 * @author George Papkevich
 * @version 1.0
 * @since 1.0
 */
public interface RequestService {

    /**
     * 
     * Purpose of this method is to get specific amount of {@code Request} from
     * database depending on {@code pageNumber}. This method returns
     * {@code ItemsTransferObject} which contains list of {@code Request} and total
     * amount of pages.
     * 
     * @param pageNumber number of current page.
     * @param userId     id is used to decide which user's requests to take from
     *                   database.
     * @param isAdmin    boolean value to decide if we need to take all unhandled
     *                   requests from database.
     * @return {@code ItemsTransferObject} data transfer object which contains list
     *         of {@code Request} and amount of pages.
     * @throws ServiceException
     */
    ItemsDto<Request> getRequestsForUser(int pageNumber, Long userId, boolean isAdmin) throws ServiceException;

    /**
     * This method returns single request for admin.
     * 
     * @param requestId request which will be handled.
     * @return {@code Optional<Request>} optional value of request.
     * @throws ServiceException
     */
    Optional<Request> getRequest(Long requestId) throws ServiceException;

    /**
     * This method takes data for attach specific room to existing request.
     * 
     * @param requestId request in which room will be attached.
     * @param roomId    id of room which will be attached to request.
     * @throws ServiceException
     */
    void handleRoomRequest(Long requestId, Long roomId) throws ServiceException;

    /**
     * This method transfers {@code Request} to Dao layer so that the request can be
     * created in database later.
     * 
     * @param request request which will be created in database.
     * @throws ServiceException
     */
    void createRoomRequest(Request request) throws ServiceException;

}
