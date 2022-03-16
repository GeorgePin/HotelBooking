package com.epam.hotelbooking.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.RoomDaoImpl;
import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.mapper.RoomRowMapper;

public class RoomServiceImpl implements RoomService {

    private static final Logger LOGGER = LogManager.getLogger(RoomServiceImpl.class);

    @Override
    public ItemsTransferObject getRoomsForSinglePage(int startElement, boolean isForHandling) throws ServiceException {
        LOGGER.info("Getting rooms for single page");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomDaoImpl dao = daoHelper.createRoomDao(new RoomRowMapper());
            ItemsTransferObject itemsTransferObject;
            itemsTransferObject = isForHandling ? dao.getFreeRoomsForSinglePage(startElement)
                    : dao.getRoomsWithPrices(startElement);
            daoHelper.endTransaction();
            LOGGER.info("Rooms were successfully found");
            return itemsTransferObject;
        } catch (DaoException exception) {
            throw new ServiceException("Error during getting free rooms for single page", exception);
        }
    }

    @Override
    public void createRoom(Room room) throws ServiceException {
        LOGGER.info("Creating new room");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomDaoImpl dao = daoHelper.createRoomDao(new RoomRowMapper());
            dao.create(room);
            daoHelper.endTransaction();
            LOGGER.info("Room was created succesfully");
        } catch (DaoException exception) {
            throw new ServiceException("Error during creating new room", exception);
        }
    }

    @Override
    public void deleteRoom(Long roomId) throws ServiceException {
        LOGGER.info("Deleting room");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomDaoImpl dao = daoHelper.createRoomDao(new RoomRowMapper());
            dao.delete(roomId);
            daoHelper.endTransaction();
            LOGGER.info("Room was deleted succesfully");
        } catch (DaoException exception) {
            throw new ServiceException("Error during deleting room", exception);
        }
    }

    @Override
    public void unblockRoom(Long roomId) throws ServiceException {
        LOGGER.info("Unblock room");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomDaoImpl dao = daoHelper.createRoomDao(new RoomRowMapper());
            dao.unblockRoom(roomId);
            daoHelper.endTransaction();
            LOGGER.info("Room was unblocked succesfully");
        } catch (DaoException exception) {
            throw new ServiceException("Error during unblocking room", exception);
        }
    }
}
