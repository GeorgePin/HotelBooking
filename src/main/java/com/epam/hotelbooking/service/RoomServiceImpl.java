package com.epam.hotelbooking.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.RoomDao;
import com.epam.hotelbooking.entity.ItemsDto;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.mapper.IdentifiableRoomRowMapper;
import com.epam.hotelbooking.mapper.RoomRowMapper;
import com.epam.hotelbooking.mapper.RoomWithPriceRowMapper;

public class RoomServiceImpl implements RoomService {

    private static final Logger LOGGER = LogManager.getLogger(RoomServiceImpl.class);

    @Override
    public ItemsDto<Room> getRoomsForSinglePage(int startElement, boolean isForHandling) throws ServiceException {
        LOGGER.info("Getting rooms for single page");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomDao dao = daoHelper.createRoomDao(new RoomWithPriceRowMapper());
            ItemsDto<Room> itemsTransferObject;
            itemsTransferObject = isForHandling
                    ? dao.getFreeRoomsForSinglePage(startElement)
                    : dao.getRoomsWithPrices(startElement);
            daoHelper.endTransaction();
            LOGGER.info("Rooms were successfully found");
            return itemsTransferObject;
        } catch (DaoException exception) {
            throw new ServiceException("Error during getting free rooms for single page", exception);
        }
    }

    @Override
    public boolean createRoom(Room room) throws ServiceException {
        LOGGER.info("Creating new room");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomDao dao = daoHelper.createRoomDao(new RoomRowMapper());
            LOGGER.debug(room.toString());
            boolean wasRoomCreated = dao.create(room);
            daoHelper.endTransaction();
            if (wasRoomCreated) {
                LOGGER.info("Room was created succesfully");
                return wasRoomCreated;
            } else {
                return false;
            }
        } catch (DaoException exception) {
            throw new ServiceException("Error during creating new room", exception);
        }
    }

    @Override
    public void deleteRoom(Long roomId) throws ServiceException {
        LOGGER.info("Deleting room");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomDao dao = daoHelper.createRoomDao(new RoomWithPriceRowMapper());
            dao.delete(roomId);
            daoHelper.endTransaction();
            LOGGER.info("Room was deleted succesfully");
        } catch (DaoException exception) {
            throw new ServiceException("Error during deleting room", exception);
        }
    }

    @Override
    public Optional<Room> readRoom(Long roomId) throws ServiceException {
        LOGGER.info("Reading room");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomDao dao = daoHelper.createRoomDao(new RoomRowMapper());
            Optional<Room> room = dao.read(roomId);
            daoHelper.endTransaction();
            if (room.isPresent()) {
                LOGGER.info("Room succesfully read ");
                return room;
            } else {
                return Optional.empty();
            }
        } catch (DaoException exception) {
            throw new ServiceException("Error during reading room", exception);
        }
    }

    @Override
    public boolean editRoom(Room room) throws ServiceException {
        LOGGER.info("Editing room");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomDao dao = daoHelper.createRoomDao(new IdentifiableRoomRowMapper());
            boolean wasRoomEdited = dao.editRoom(room);
            daoHelper.endTransaction();
            if (wasRoomEdited) {
                LOGGER.info("Room updated ");
                return wasRoomEdited;
            } else {
                return false;
            }
        } catch (DaoException exception) {
            throw new ServiceException("Error during updating room", exception);
        }
    }

    @Override
    public void setStateOfRoom(Long roomId, boolean state) throws ServiceException {
        LOGGER.info("Cnahging state of room");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomDao dao = daoHelper.createRoomDao(new RoomWithPriceRowMapper());
            if (state) {
                dao.blockRoom(roomId);
            } else {
                dao.unblockRoom(roomId);
            }
            daoHelper.endTransaction();
            LOGGER.info("Room state was changed succesfully");
        } catch (DaoException exception) {
            throw new ServiceException("Error during changing state of room", exception);
        }
    }
}
