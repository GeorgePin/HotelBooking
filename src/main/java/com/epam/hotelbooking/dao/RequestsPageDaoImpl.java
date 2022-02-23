package com.epam.hotelbooking.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RequestRowMapper;

public class RequestsPageDaoImpl extends AbstractDao<Request> implements RequestsPageDao {
    private static final int RECORDS_PER_PAGE = 5;
    private static final String GET_ALL_REQUESTS = "select * from reservation limit ?, ?";

    public RequestsPageDaoImpl(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    @Override
    public List<Request> getRequests(int startElement) throws SQLException, DaoException {
        List<Request> requests = executeQuery(GET_ALL_REQUESTS, new RequestRowMapper(), startElement, RECORDS_PER_PAGE);
        return requests;
    }

    @Override
    public Optional<Request> get(long id) {
        return null;
        // TODO Auto-generated method stub
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
