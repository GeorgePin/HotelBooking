package com.epam.hotelbooking.dao;

import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.mapper.ClientRowMapper;

public class ClientDaoImpl extends AbstractDao<User> implements ClientDao {
    private static final String GET_ALL_CLIENTS = "select user.id, user.name, user.surname, user.login, user.is_Blocked from user where is_admin='0'";

    public ClientDaoImpl(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    @Override
    public List<User> getAllClients() throws Exception {
        return executeQuery(GET_ALL_CLIENTS, new ClientRowMapper());
    }

    @Override
    public Optional<User> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(User t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(User t, String[] params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(User t) {
        // TODO Auto-generated method stub

    }

}