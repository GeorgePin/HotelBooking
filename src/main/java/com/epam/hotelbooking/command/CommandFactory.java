package com.epam.hotelbooking.command;

import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.service.UserServiceImpl;

public class CommandFactory {

    public Command createCommand(String command) {
        switch (command) {
        case "login":
            return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()));
        case "requsetRoom":
            return new RequestRoomCommand();
        default:
            throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}
