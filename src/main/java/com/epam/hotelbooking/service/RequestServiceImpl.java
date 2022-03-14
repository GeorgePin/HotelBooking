package com.epam.hotelbooking.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.RequestDaoImpl;
import com.epam.hotelbooking.dao.RoomDaoImpl;
import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.mapper.AdminRequestRowMapper;
import com.epam.hotelbooking.mapper.ClientRequestRowMapper;
import com.epam.hotelbooking.mapper.RoomRowMapper;

public class RequestServiceImpl implements RequestService {

    private static final Logger LOGGER = LogManager.getLogger(RequestServiceImpl.class);

    @Override
    public ItemsTransferObject getRequestsForUser(int pageNumber, Long userId, boolean isAdmin)
            throws ServiceException {
        LOGGER.info("Getting requests for user");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            ItemsTransferObject transferObject;
            if (isAdmin) {
                RequestDaoImpl adminRequestDao = daoHelper.createRequestDao(new AdminRequestRowMapper());
                transferObject = adminRequestDao.getUnapprovedRequestsForAdmin(pageNumber);
            } else {
                RequestDaoImpl userRequestDao = daoHelper.createRequestDao(new ClientRequestRowMapper());
                transferObject = userRequestDao.getRequestsForClient(pageNumber, userId);
            }
            daoHelper.endTransaction();
            LOGGER.info("Requests were founded successfully");
            return transferObject;
        } catch (DaoException exception) {
            throw new ServiceException("Error during getting requests for user", exception);
        }
    }

    @Override
    public Optional<Request> getRequest(Long requestId) throws ServiceException {
        LOGGER.info("Getting single request for handling");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RequestDaoImpl requestDao = daoHelper.createRequestDao(new AdminRequestRowMapper());
            Optional<Request> request = requestDao.read(requestId);
            daoHelper.endTransaction();
            LOGGER.info("Request founded successfully");
            return request;
        } catch (DaoException exception) {
            throw new ServiceException("Error during getting request", exception);
        }
    }

    @Override
    public void handleRoomRequest(Long requestId, Long roomId) throws ServiceException {
        LOGGER.info("Handling room request");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RequestDaoImpl requestDao = daoHelper.createRequestDao(new ClientRequestRowMapper());
            RoomDaoImpl roomDao = daoHelper.createRoomDao(new RoomRowMapper());
            roomDao.blockRoom(roomId);
            requestDao.insertRoomIntoRequest(requestId, roomId);
            daoHelper.endTransaction();
            LOGGER.info("Room request handled successfully");
        } catch (DaoException exception) {
            throw new ServiceException("Error during request handling", exception);
        }
    }

    @Override
    public void createRoomRequest(Request request) throws ServiceException {
        LOGGER.info("Creating new room request");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RequestDaoImpl requestDao = daoHelper.createRequestDao(new ClientRequestRowMapper());
            requestDao.create(request);
            daoHelper.endTransaction();
            LOGGER.info("Room request created successfully");
        } catch (DaoException exception) {
            throw new ServiceException("Error during creating request", exception);
        }
    }
}
