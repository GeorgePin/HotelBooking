package com.epam.hotelbooking.dao;

import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.RoomsAmount;
import com.epam.hotelbooking.mapper.RoomsAmountRowMapper;

public class AmountOfRoomsDaoImpl extends AbstractDao<RoomsAmount> implements AmountOfRoomsDao {
    private static final String TOTAL_REQUESTS_AMOUNT = "select count(*) from room";

    public AmountOfRoomsDaoImpl(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    @Override
    public Optional<RoomsAmount> getNumberOfRooms() throws Exception {
        return executeForSingleResult(TOTAL_REQUESTS_AMOUNT, new RoomsAmountRowMapper());
    }

    @Override
    public Optional<RoomsAmount> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<RoomsAmount> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(RoomsAmount t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(RoomsAmount t, String[] params) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(RoomsAmount t) {
        // TODO Auto-generated method stub
        
    }
}