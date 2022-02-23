package com.epam.hotelbooking.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.Room;

public class DeleteRoomDaoImpl extends AbstractDao<Room> implements DeleteRoomDao {
    private static final String DELETE_ROOM = "delete from room where id=?";

    public DeleteRoomDaoImpl(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    @Override
    public boolean deleteRoom(Long roomId) throws SQLException {
        return executeQueryWithoutReturnValue(DELETE_ROOM, roomId);
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
