package com.epam.hotelbooking.service;

import java.util.List;

import com.epam.hotelbooking.dao.ClientDao;
import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.entity.User;

public class ClientServiceImpl implements ClientService {

    private DaoHelperFactory daoHelperFactory;

    public ClientServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<User> getAllClients() throws Exception {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            ClientDao dao = helper.createClientDao();
            List<User> listOfClients = dao.getAllClients();
            helper.endTransaction();
            return listOfClients;
        }
    }
}
