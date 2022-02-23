package com.epam.hotelbooking.service;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.dao.UserDao;

public class CreateUserServiceImpl implements CreateUserSevice {
    private DaoHelperFactory daoHelperFactory;

    public CreateUserServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public boolean createUser(String name, String surname, String login, String password) throws Exception {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            boolean isRoomDeleted = dao.createUser(name, surname, login, password);
            helper.endTransaction();
            return isRoomDeleted;
        }
    }
}
