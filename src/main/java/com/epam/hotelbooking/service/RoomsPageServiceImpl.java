package com.epam.hotelbooking.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.epam.hotelbooking.dao.DaoHelper;
import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.dao.RoomsPageDao;
import com.epam.hotelbooking.entity.Room;

public class RoomsPageServiceImpl implements RoomsPageService {

    private DaoHelperFactory daoHelperFactory;

    public RoomsPageServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<Room> getRooms(int startElement) throws ClassNotFoundException, SQLException, IOException, Exception{
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            RoomsPageDao dao = helper.createRoomsPageDao();
            List<Room> rooms = dao.getRooms(startElement);
            helper.endTransaction();
            return rooms;
        }
    }

}
