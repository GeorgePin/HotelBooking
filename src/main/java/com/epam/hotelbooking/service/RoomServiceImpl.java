package com.epam.hotelbooking.service;

import java.util.List;

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
            List<Room> listOfRooms = isForHandling ? dao.getFreeRoomsForSinglePage(startElement)
                    : dao.getRoomsWithPrices(startElement);
            Integer amountOfAllRooms = dao.getAmountOfPages();
            ItemsTransferObject transferObject = new ItemsTransferObject(listOfRooms, amountOfAllRooms);
            daoHelper.endTransaction();
            return transferObject;
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
}
