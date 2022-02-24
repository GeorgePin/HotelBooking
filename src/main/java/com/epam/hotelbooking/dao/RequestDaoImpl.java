package com.epam.hotelbooking.dao;

import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.mapper.RequestRowMapper;

public class RequestDaoImpl extends AbstractDao<Request> implements RequestDao {
    private static final String FIND_REQUEST_BY_ID = "select * from reservation where id = ?";

    public RequestDaoImpl(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    @Override
    public Optional<Request> getRequest(Long requestId) throws Exception {
        Optional<Request> request = executeForSingleResult(FIND_REQUEST_BY_ID, new RequestRowMapper(), requestId);
        return request;
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
