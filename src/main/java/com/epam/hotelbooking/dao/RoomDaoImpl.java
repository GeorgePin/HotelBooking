package com.epam.hotelbooking.dao;

import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.EntityType;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RowMapper;

public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {
    private static final String IS_ROOM_BLOCKED_FILTER = "is_blocked";
    private static final String BLOCK_ROOM = "update room set is_blocked = '1' where id = ?";
    private static final String DELETE_ROOM = "delete from room where id=?";
    private static final String CREATE_NEW_ROOM = "insert into room(capacity, type, number,"
            + " is_blocked, room_price_id) values(?, ?, ?, ?, ?)";
    private static final String GET_ROOMS_WITH_PRICES = "select room.id, room.capacity, room.type,"
            + " room.number, room.is_blocked, room.room_price_id, room_price.price, room_price.valid_from"
            + " from room inner join room_price on room_price.id=room.room_price_id ORDER BY `id` limit ?, ?";

    public RoomDaoImpl(ProxyConnection proxyConnection, RowMapper<Room> rowMapper) {
        super(proxyConnection, rowMapper);
    }

    @Override
    public void create(Room item) throws DaoException {
        int capacity = item.getCapacity();
        String roomClass = item.getType()
                .toString()
                .toLowerCase();
        int numberOfRoom = item.getNumber();
        boolean isBlocked = item.getIsBlocked();
        RoomPrice roomPriceId = item.getRoomPrice();
        executeQueryWithoutReturnValue(CREATE_NEW_ROOM, capacity, roomClass, numberOfRoom, isBlocked, roomPriceId);
    }

    @Override
    public Optional<Room> read(Long itemId) throws DaoException {
        throw new UnsupportedOperationException(NO_IMPLEMENTATION);
    }

    @Override
    public void update(Long itemId, String query, Object... params) throws DaoException {
        executeQueryWithoutReturnValue(query, itemId, params);
    }

    @Override
    public void delete(Long itemId) throws DaoException {
        executeQueryWithoutReturnValue(DELETE_ROOM, itemId);
    }

    @Override
    public void blockRoom(Long roomId) throws DaoException {
        update(roomId, BLOCK_ROOM);
    }

    @Override
    public List<Room> getFreeRoomsForSinglePage(int pageNumber) throws DaoException {
        int startElement = (pageNumber - 1) * RECORDS_PER_PAGE;
        return super.getItemsForSinglePage(startElement, EntityType.ROOM, IS_ROOM_BLOCKED_FILTER, ZERO);
    }

    @Override
    public Integer getAmountOfPages() throws DaoException {
        return super.getAmountOfPages(EntityType.ROOM);
    }

    @Override
    public final List<Room> getRoomsWithPrices(int pageNumber) throws DaoException {
        int startElement = (pageNumber - 1) * RECORDS_PER_PAGE;
        return executeQuery(GET_ROOMS_WITH_PRICES, startElement, RECORDS_PER_PAGE);
    }
}