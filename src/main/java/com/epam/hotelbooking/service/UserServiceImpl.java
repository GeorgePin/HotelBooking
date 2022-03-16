package com.epam.hotelbooking.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.UserDaoImpl;
import com.epam.hotelbooking.entity.ItemsTransferObject;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.mapper.ClientRowMapper;
import com.epam.hotelbooking.mapper.UserRowMapper;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        LOGGER.info("Starting login procedure");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            UserDaoImpl dao = daoHelper.createUserDao(new UserRowMapper());
            Optional<User> user = dao.findUserByLoginAndPassword(login, password);
            daoHelper.endTransaction();
            LOGGER.info("Login data found");
            return user;
        } catch (DaoException exception) {
            throw new ServiceException("Error during login procedure", exception);
        }
    }

    @Override
    public void banUser(Long userId) throws ServiceException {
        LOGGER.info("Strating block user procedure");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            UserDaoImpl dao = daoHelper.createUserDao(new UserRowMapper());
            dao.banUser(userId);
            daoHelper.endTransaction();
            LOGGER.info("User was blocked successfully");
        } catch (DaoException exception) {
            throw new ServiceException("Error during user block", exception);
        }
    }

    @Override
    public ItemsTransferObject getAllClients(int pageNumber) throws ServiceException {
        LOGGER.info("Getting all clients");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            UserDaoImpl userDao = daoHelper.createUserDao(new ClientRowMapper());
            ItemsTransferObject itemsTransferObject = userDao.getAllClients(pageNumber);
            daoHelper.endTransaction();
            LOGGER.info("Clients were successfully found");
            return itemsTransferObject;
        } catch (DaoException exception) {
            throw new ServiceException("Error during reading all clients", exception);
        }
    }

    @Override
    public void createUser(User user) throws ServiceException {
        LOGGER.info("Creating new user");
        try (DaoHelper daoHelper = new DaoHelper()) {
            daoHelper.startTransaction();
            UserDaoImpl userDao = daoHelper.createUserDao(new UserRowMapper());
            userDao.create(user);
            daoHelper.endTransaction();
            LOGGER.info("User was created successfully");
        } catch (DaoException exception) {
            throw new ServiceException("Error during registratig new user", exception);
        }
    }
}
