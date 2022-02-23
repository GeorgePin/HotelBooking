package com.epam.hotelbooking.dao;

import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.Room;

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

    @Override
    public Optional<Room> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Room> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Room t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Room t, String[] params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Room t) {
        // TODO Auto-generated method stub

    }

}
