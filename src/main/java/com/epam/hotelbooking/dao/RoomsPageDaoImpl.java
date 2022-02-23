package com.epam.hotelbooking.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RoomRowMapper;

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
