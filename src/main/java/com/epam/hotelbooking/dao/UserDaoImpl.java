package com.epam.hotelbooking.dao;

import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.mapper.UserRowMapper;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "select * from user where login = ? and password = ?";

    public UserDaoImpl(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws Exception {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserRowMapper(), login, password);
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

    @Override
    public Optional<User> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
