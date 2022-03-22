package com.epam.hotelbooking.dao;

import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.EntityType;
import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RowMapper;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "select user.id, user.is_admin,"
            + " user.is_blocked from user where login = ? and password = MD5(?) ";

    private static final String CREATE_NEW_USER = "insert into user(name, surname, login, password)"
            + " values(?, ?, ?, MD5(?))";

    private static final String BAN_USER = "update user set is_blocked='1' where id=?";

    private static final String UNBAN_USER = "update user set is_blocked='0' where id=?";

    private static final String GET_ALL_CLIENTS = "select user.id, user.name, user.surname, "
            + "user.login, user.is_Blocked from user where is_admin='0' limit ?, ?";

    private static final String IS_ADMIN_FILTER = "is_admin";

    private static final String DOES_USER_EXISTS = "select * from user where user.login=?";

    public UserDaoImpl(ProxyConnection proxyConnection, RowMapper<User> rowMapper) {
        super(proxyConnection, rowMapper);
    }

    @Override
    public boolean create(User item) throws DaoException {
        String userLogin = item.getLogin();
        if (doesUserExists(userLogin).isPresent()) {
            return false;
        }
        String userName = item.getName();
        String userSurname = item.getSurname();
        String userPassword = item.getPassword();
        executeQueryWithoutReturnValue(CREATE_NEW_USER, userName, userSurname, userLogin, userPassword);
        return true;
    }

    @Override
    public Optional<User> read(Long itemId) throws DaoException {
        throw new UnsupportedOperationException(NO_IMPLEMENTATION);
    }

    @Override
    public void update(String query, Object... params) throws DaoException {
        executeQueryWithoutReturnValue(query, params);
    }

    @Override
    public void delete(Long itemId) throws DaoException {
        throw new UnsupportedOperationException(NO_IMPLEMENTATION);
    }

    @Override
    public ItemsTransferObject getAllClients(int pageNumber) throws DaoException {
        int startElement = (pageNumber - 1) * RECORDS_PER_PAGE;
        Integer amountOfPages = super.getAmountOfPages(EntityType.USER, IS_ADMIN_FILTER, ZERO);
        List<User> listOfUsers = executeQuery(GET_ALL_CLIENTS, startElement, RECORDS_PER_PAGE);
        return new ItemsTransferObject(listOfUsers, amountOfPages);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }

    @Override
    public void banUser(Long userId) throws DaoException {
        update(BAN_USER, userId);
    }

    @Override
    public void unbanUser(Long userId) throws DaoException {
        update(UNBAN_USER, userId);
    }

    private Optional<User> doesUserExists(String login) throws DaoException {
        return executeForSingleResult(DOES_USER_EXISTS, login);
    }
}
