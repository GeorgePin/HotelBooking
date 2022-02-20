package com.epam.hotelbooking.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.Request;

public class RequestRoomDaoImpl extends AbstractDao<Request> implements RequestRoomDao {
    private static final String CREATE_NEW_ROOM_REQUEST = "insert into reservation (room_capacity,room_class,start_date,end_date,user_id) values (?,?,?,?,?)";

    public RequestRoomDaoImpl(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    public boolean createRoomRequest(int roomCapacity, String roomClass, Date startDate, Date endDate, Long userId)
            throws SQLException {
        return executeQueryWithoutReturnValue(CREATE_NEW_ROOM_REQUEST, roomCapacity, roomClass, startDate, endDate,
                userId);
    }

    @Override
    public void update(Request t, String[] params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Request t) {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<Request> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Request> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Request t) {
        // TODO Auto-generated method stub

    }
}
