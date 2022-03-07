package com.epam.hotelbooking.service;

import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.RequestDaoImpl;
import com.epam.hotelbooking.dao.RoomDaoImpl;
import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.mapper.RequestRowMapper;
import com.epam.hotelbooking.mapper.RoomRowMapper;

public class RequestServiceImpl implements RequestService {

    @Override
    public ItemsTransferObject getRequestsForUser(int pageNumber, boolean isAdmin) throws ServiceException {
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RequestDaoImpl requestDao = daoHelper.createRequestDao(new RequestRowMapper());
            List<Request> listOfRequests = isAdmin ? requestDao.getUnapprovedRequestsForAdmin(pageNumber)
                    : requestDao.getRequestsForClient(pageNumber);
            Integer amountOfAllRequests = requestDao.getAmountOfPages();
            ItemsTransferObject transferObject = new ItemsTransferObject(listOfRequests, amountOfAllRequests);
            daoHelper.endTransaction();
            return transferObject;
        } catch (DaoException exception) {
            throw new ServiceException("Error during getting requests for user", exception);
        }
    }

    @Override
    public Optional<Request> getRequest(Long requestId) throws ServiceException {
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RequestDaoImpl requestDao = daoHelper.createRequestDao(new RequestRowMapper());
            Optional<Request> request = requestDao.read(requestId);
            daoHelper.endTransaction();
            return request;
        } catch (DaoException exception) {
            throw new ServiceException("Error during getting request", exception);
        }
    }

    @Override
    public void handleRoomRequest(Long requestId, Long roomId) throws ServiceException {
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RequestDaoImpl requestDao = daoHelper.createRequestDao(new RequestRowMapper());
            RoomDaoImpl roomDao = daoHelper.createRoomDao(new RoomRowMapper());
            roomDao.blockRoom(roomId);
            requestDao.insertRoomIntoRequest(requestId, roomId);
            daoHelper.endTransaction();
        } catch (DaoException exception) {
            throw new ServiceException("Error during request handling", exception);
        }
    }

    @Override
    public void createRoomRequest(Request request) throws ServiceException {
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RequestDaoImpl requestDao = daoHelper.createRequestDao(new RequestRowMapper());
            requestDao.create(request);
            daoHelper.endTransaction();
        } catch (DaoException exception) {
            throw new ServiceException("Error during creating request", exception);
        }
    }
}
