package com.epam.hotelbooking.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.ClientRowMapper;
import com.epam.hotelbooking.mapper.RowMapper;
import com.epam.hotelbooking.mapper.UserRowMapper;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "select * from user where login = ? and password = ?";
    private static final String CREATE_NEW_USER = "insert into user(name, surname, login, password) values(?, ?, ?, ?)";
    private static final String BAN_USER = "update user set is_blocked='1' where id=?";
    private static final String GET_ALL_CLIENTS = "select user.id, user.name, user.surname, user.login, user.is_Blocked from user where is_admin='0'";

    public UserDaoImpl(ProxyConnection proxyConnection, RowMapper<User> rowMapper) {
        super(proxyConnection, rowMapper);
    }

    @Override
    public boolean create(User item) throws DaoException {
        String userName = item.getName();
        String userSurname = item.getSurname();
        String userLogin = item.getLogin();
        String userPassword = item.getPassword();
        try {
            return executeQueryWithoutReturnValue(CREATE_NEW_USER, userName, userSurname, userLogin, userPassword);
        } catch (SQLException exception) {
            throw new DaoException("Exception during user creating", exception);
        }
    }

    @Override
    public Optional<User> read(Long itemId) throws DaoException {
        throw new UnsupportedOperationException(NO_IMPLEMENTATION);
    }

    @Override
    public boolean update(Long itemId, String query, Object... params) throws DaoException {
        try {
            return executeQueryWithoutReturnValue(query, itemId, params);
        } catch (SQLException exception) {
            throw new DaoException("Exception updating user", exception);
        }
    }

    @Override
    public boolean delete(Long itemId) throws DaoException {
        throw new UnsupportedOperationException(NO_IMPLEMENTATION);
    }

    @Override
    public List<User> getAllClients() throws DaoException {
        try {
            return executeQuery(GET_ALL_CLIENTS, new ClientRowMapper());
        } catch (SQLException exception) {
            throw new DaoException("Exception during getting all clients", exception);
        }
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        try {
            return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserRowMapper(), login, password);
        } catch (SQLException exception) {
            throw new DaoException("Exception during authorization", exception);
        }
    }

    @Override
    public boolean banUser(Long userId) throws DaoException {
        return update(userId, BAN_USER);
    }
}
