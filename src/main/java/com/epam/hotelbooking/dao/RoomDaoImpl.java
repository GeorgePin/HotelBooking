package com.epam.hotelbooking.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RoomRowMapper;
import com.epam.hotelbooking.mapper.RowMapper;

public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {
    private static final String GET_ALL_FREE_ROOMS = "select * from room where is_blocked='0'";
    private static final String BLOCK_ROOM = "update room set is_blocked = '1' where id = ?";
    private static final String DELETE_ROOM = "delete from room where id=?";
    private static final String CREATE_NEW_ROOM = "insert into room(capacity, type, number, is_blocked, room_price_id) values(?, ?, ?, ?, ?)";

    public RoomDaoImpl(ProxyConnection proxyConnection, RowMapper<Room> rowMapper) {
        super(proxyConnection, rowMapper);
    }

    @Override
    public boolean create(Room item) throws DaoException {
        int capacity = item.getCapacity();
        String roomClass = item.getType()
                .toString()
                .toLowerCase();
        int numberOfRoom = item.getNumber();
        boolean isBlocked = item.getIsBlocked();
        RoomPrice roomPriceId = item.getRoomPrice();
        try {
            return executeQueryWithoutReturnValue(CREATE_NEW_ROOM, capacity, roomClass, numberOfRoom, isBlocked,
                    roomPriceId);
        } catch (SQLException exception) {
            throw new DaoException("Exception during creating item", exception);
        }
    }

    @Override
    public Optional<Room> read(Long itemId) throws DaoException {
        throw new UnsupportedOperationException(NO_IMPLEMENTATION);
    }

    @Override
    public boolean update(Long itemId, String query, Object... params) throws DaoException {
        try {
            return executeQueryWithoutReturnValue(query, itemId, params);
        } catch (SQLException exception) {
            throw new DaoException("Exception during updating item", exception);
        }
    }

    @Override
    public boolean delete(Long itemId) throws DaoException {
        try {
            return executeQueryWithoutReturnValue(DELETE_ROOM, itemId);
        } catch (SQLException exception) {
            throw new DaoException("Exception during deleting item", exception);
        }
    }

    @Override
    public List<Room> getFreeRooms() throws DaoException {
        List<Room> freeRooms;
        try {
            freeRooms = executeQuery(GET_ALL_FREE_ROOMS, new RoomRowMapper());
        } catch (SQLException exception) {
            throw new DaoException("Exception during getting free rooms", exception);
        }
        return freeRooms;
    }

    @Override
    public boolean blockRoom(Long roomId) throws DaoException {
        return update(roomId, BLOCK_ROOM);
    }
}