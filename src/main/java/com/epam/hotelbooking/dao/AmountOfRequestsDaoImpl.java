package com.epam.hotelbooking.dao;

import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.RequestsAmount;
import com.epam.hotelbooking.mapper.RequestsAmountRowMapper;

public class AmountOfRequestsDaoImpl extends AbstractDao<RequestsAmount> implements AmountOfRequestsDao {
    private static final String TOTAL_REQUESTS_AMOUNT = "select count(*) from reservation";

    public AmountOfRequestsDaoImpl(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    @Override
    public Optional<RequestsAmount> getNumberOfRequests() throws Exception {
        return executeForSingleResult(TOTAL_REQUESTS_AMOUNT, new RequestsAmountRowMapper());
    }

    @Override
    public Optional<RequestsAmount> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<RequestsAmount> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(RequestsAmount t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(RequestsAmount t, String[] params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(RequestsAmount t) {
        // TODO Auto-generated method stub

    }
}
