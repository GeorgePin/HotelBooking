package com.epam.hotelbooking.dao;

import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.ItemsDto;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RowMapper;

public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {

    private static final String IS_DELETED_FILTER = "is_deleted";

    private static final String IS_ROOM_BLOCKED_FILTER = "is_blocked";

    private static final String ROOM_CAPACITY_FILTER = "room.capacity";

    private static final String BLOCK_ROOM = "update room set is_blocked = '1' where id = ?";

    private static final String UNBLOCK_ROOM = "update room set is_blocked = '0' where id = ?";

    private static final String DELETE_ROOM = "update room set is_deleted='1' where id=?";

    private static final String CREATE_NEW_ROOM = "insert into room(capacity, type, number,"
            + " is_blocked, room_price_id) values(?, ?, ?, ?, ?)";

    private static final String GET_ROOMS_WITH_PRICES = "SELECT room.*, room_price.price, room_price.valid_from "
            + "FROM room INNER JOIN room_price ON room_price.id = room.room_price_id WHERE room.is_deleted = '0' LIMIT ?, ?";

    private static final String GET_FREE_ROOMS_FOR_SINGLE_PAGE = "SELECT room.*, room_price.valid_from, room_price.price "
            + "FROM room JOIN room_price ON room_price.id = room.room_price_id "
            + "where room.is_blocked='0' and room.is_deleted='0' and room.capacity=? limit ?, ?";

    private static final String DOES_ROOM_EXISTS = "select * from room where room.number=?";

    private static final String FIND_ROOM_BY_ID = "select * from room where room.id=?";

    private static final String UPDATE_ROOM = "update room set capacity=?, type=?, number=?, "
            + "room_price_id=? where room.id=?";

    public RoomDaoImpl(ProxyConnection proxyConnection, RowMapper<Room> rowMapper) {
        super(proxyConnection, rowMapper);
    }

    @Override
    public boolean create(Room item) throws DaoException {
        int numberOfRoom = item.getNumber();
        if (doesRoomExists(numberOfRoom).isPresent()) {
            return false;
        }
        int capacity = item.getCapacity();
        String roomClass = item.getRoomClass();
        boolean isBlocked = item.getIsBlocked();
        Long roomPriceId = item.getRoomPriceId();
        executeQueryWithoutReturnValue(CREATE_NEW_ROOM, capacity, roomClass, numberOfRoom, isBlocked, roomPriceId);
        return true;
    }

    @Override
    public Optional<Room> read(Long itemId) throws DaoException {
        return executeForSingleResult(FIND_ROOM_BY_ID, itemId);
    }

    @Override
    public void update(String query, Object... params) throws DaoException {
        executeQueryWithoutReturnValue(query, params);
    }

    @Override
    public void delete(Long itemId) throws DaoException {
        executeQueryWithoutReturnValue(DELETE_ROOM, itemId);
    }

    @Override
    public void blockRoom(Long roomId) throws DaoException {
        update(BLOCK_ROOM, roomId);
    }

    @Override
    public ItemsDto<Room> getFreeRoomsForSinglePage(int pageNumber, Integer roomCapacity) throws DaoException {
        int startElement = (pageNumber - 1) * RECORDS_PER_PAGE;
        Integer amountOfPages = super.amountOfPagesQueryBuilding(Room.TABLE_NAME, IS_ROOM_BLOCKED_FILTER,
                Integer.toString(0), IS_DELETED_FILTER, Integer.toString(0), ROOM_CAPACITY_FILTER,
                roomCapacity.toString());
        List<Room> listOfRooms = super.executeQuery(GET_FREE_ROOMS_FOR_SINGLE_PAGE, roomCapacity, startElement,
                RECORDS_PER_PAGE);
        return new ItemsDto<>(listOfRooms, amountOfPages);
    }

    @Override
    public ItemsDto<Room> getRoomsWithPrices(int pageNumber) throws DaoException {
        int startElement = (pageNumber - 1) * RECORDS_PER_PAGE;
        Integer amountOfPages = super.amountOfPagesQueryBuilding(Room.TABLE_NAME, IS_DELETED_FILTER,
                Integer.toString(0));
        List<Room> listOfRooms = executeQuery(GET_ROOMS_WITH_PRICES, startElement, RECORDS_PER_PAGE);
        return new ItemsDto<>(listOfRooms, amountOfPages);
    }

    @Override
    public void unblockRoom(Long itemId) throws DaoException {
        executeQueryWithoutReturnValue(UNBLOCK_ROOM, itemId);
    }

    @Override
    public boolean editRoom(Room room) throws DaoException {
        int roomNumber = room.getNumber();
        Optional<Room> roomFromDb = doesRoomExists(roomNumber);
        if (roomFromDb.isEmpty()
                || roomFromDb.get()
                        .getId()
                        .equals(room.getId())) {
            update(UPDATE_ROOM, room.getCapacity(), room.getRoomClass(), room.getNumber(), room.getRoomPriceId(),
                    room.getId());
            return true;
        } else {
            return false;
        }
    }

    private Optional<Room> doesRoomExists(Integer numberOfRoom) throws DaoException {
        return executeForSingleResult(DOES_ROOM_EXISTS, numberOfRoom);
    }
}