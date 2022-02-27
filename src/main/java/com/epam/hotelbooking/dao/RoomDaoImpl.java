package com.epam.hotelbooking.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomsAmount;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RoomRowMapper;
import com.epam.hotelbooking.mapper.RoomsAmountRowMapper;

public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {
    private static final String GET_ALL_FREE_ROOMS = "select * from room where is_blocked='0'";
    private static final String BLOCK_ROOM = "update room SET is_blocked = '1' where id = ?";

    protected RoomDaoImpl(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public List<Room> getFreeRooms() throws Exception {
        List<Room> freeRooms = executeQuery(GET_ALL_FREE_ROOMS, new RoomRowMapper());
        return freeRooms;
    }

}

public class DeleteRoomDaoImpl extends AbstractDao<Room> implements DeleteRoomDao {
    private static final String DELETE_ROOM = "delete from room where id=?";

    public DeleteRoomDaoImpl(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    @Override
    public boolean deleteRoom(Long roomId) throws SQLException {
        return executeQueryWithoutReturnValue(DELETE_ROOM, roomId);
    }

    public class CreateRoomDaoImpl extends AbstractDao<Room> implements CreateRoomDao {
        private static final String TOTAL_REQUESTS_AMOUNT = "insert into room(capacity, type, number, is_blocked, room_price_id) values(?, ?, ?, ?, ?)";

        public CreateRoomDaoImpl(ProxyConnection proxyConnection) {
            super(proxyConnection);
        }

        @Override
        public boolean createRoom(Room room) throws Exception {
            int capacity = room.getCapacity();
            String roomClass = room.getType()
                    .toString()
                    .toLowerCase();
            int numberOfRoom = room.getNumber();
            boolean isBlocked = room.getIsBlocked();
            Long roomPriceId = room.getRoomPriceId();
            return executeQueryWithoutReturnValue(TOTAL_REQUESTS_AMOUNT, capacity, roomClass, numberOfRoom, isBlocked,
                    roomPriceId);
        }
    }
    public class AmountOfRoomsDaoImpl extends AbstractDao<RoomsAmount> implements AmountOfRoomsDao {
        private static final String TOTAL_REQUESTS_AMOUNT = "select count(*) from room";

        public AmountOfRoomsDaoImpl(ProxyConnection proxyConnection) {
            super(proxyConnection);
        }

        @Override
        public Optional<RoomsAmount> getNumberOfRooms() throws Exception {
            return executeForSingleResult(TOTAL_REQUESTS_AMOUNT, new RoomsAmountRowMapper());
        }
        public class RoomsPageDaoImpl extends AbstractDao<Room> implements RoomsPageDao {
            private static final int RECORDS_PER_PAGE = 5;
            private static final String GET_ALL_REQUESTS = "select room.id, room.capacity, room.type,"
                    + " room.number, room.is_blocked, room_price.price from room inner join room_price"
                    + " on room_price.id=room.room_price_id ORDER BY `id` limit ?, ?";

            public RoomsPageDaoImpl(ProxyConnection proxyConnection) {
                super(proxyConnection);
            }

            @Override
            public List<Room> getRooms(int startElement) throws DaoException, SQLException {
                List<Room> requests = executeQuery(GET_ALL_REQUESTS, new RoomRowMapper(), startElement, RECORDS_PER_PAGE);
                return requests;
            }
            public class HandleRequestDaoImpl extends AbstractDao<Request> implements HandleRequestDao {
                private static final String BLOCK_ROOM = "update room SET is_blocked = '1' where id = ?";
                private static final String ADD_ROOM_IN_REQUEST = "update reservation set room_id = ? where id = ?";
                private static final String SET_RESERVATION_APPROVED = "update reservation set is_approved = '1' where id = ?";;

                public HandleRequestDaoImpl(ProxyConnection proxyConnection) {
                    super(proxyConnection);
                }

                @Override
                public boolean handleRoomRequest(Long requestId, Long roomId) throws Exception {
                    executeQueryWithoutReturnValue(BLOCK_ROOM, roomId);
                    executeQueryWithoutReturnValue(ADD_ROOM_IN_REQUEST, roomId, requestId);
                    return executeQueryWithoutReturnValue(SET_RESERVATION_APPROVED, requestId);
                }
}