package com.epam.hotelbooking.dao;

import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.mapper.RoomRowMapper;

public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {
    private static final String GET_ALL_FREE_ROOMS = "select * from room where is_blocked='0'";

    protected RoomDaoImpl(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public List<Room> getFreeRooms() throws Exception {
        List<Room> freeRooms = executeQuery(GET_ALL_FREE_ROOMS, new RoomRowMapper());
        return freeRooms;
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
