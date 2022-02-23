package com.epam.hotelbooking.dao;

import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.Request;

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

    @Override
    public void update(Request t, String[] params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Request t) {
        // TODO Auto-generated method stub

    }
}
