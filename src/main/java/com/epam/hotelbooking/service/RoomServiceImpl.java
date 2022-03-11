package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.RoomDaoImpl;
import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.mapper.RoomRowMapper;

public class RoomServiceImpl implements RoomService {

    @Override
    public ItemsTransferObject getRoomsForSinglePage(int startElement, boolean isForHandling) throws ServiceException {
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomDaoImpl dao = daoHelper.createRoomDao(new RoomRowMapper());
            ItemsTransferObject itemsTransferObject;
            if (isForHandling) {
                itemsTransferObject = new ItemsTransferObject(dao.getFreeRoomsForSinglePage(startElement),
                        dao.getAmountOfPagesForFreeRooms());
            } else {
                itemsTransferObject = new ItemsTransferObject(dao.getRoomsWithPrices(startElement),
                        dao.getAmountOfPagesForRooms());
            }
            daoHelper.endTransaction();
            return itemsTransferObject;
        } catch (DaoException exception) {
            throw new ServiceException("Error during getting free rooms for single page", exception);
        }
    }

    @Override
    public void createRoom(Room room) throws ServiceException {
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomDaoImpl dao = daoHelper.createRoomDao(new RoomRowMapper());
            dao.create(room);
            daoHelper.endTransaction();
        } catch (DaoException exception) {
            throw new ServiceException("Error during creating new room", exception);
        }
    }

    @Override
    public void deleteRoom(Long roomId) throws ServiceException {
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomDaoImpl dao = daoHelper.createRoomDao(new RoomRowMapper());
            dao.delete(roomId);
            daoHelper.endTransaction();
        } catch (DaoException exception) {
            throw new ServiceException("Error during deleting room", exception);
        }
    }
    @Override
    public void unblockRoom(Long roomId) throws ServiceException {
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            RoomDaoImpl dao = daoHelper.createRoomDao(new RoomRowMapper());
            dao.unblockRoom(roomId);
            daoHelper.endTransaction();
        } catch (DaoException exception) {
            throw new ServiceException("Error during unblocking room", exception);
        }
    }
}
