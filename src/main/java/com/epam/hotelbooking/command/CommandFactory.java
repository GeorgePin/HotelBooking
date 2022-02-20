package com.epam.hotelbooking.command;

import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.service.RequestRoomServiceImpl;
import com.epam.hotelbooking.service.UserServiceImpl;

public class CommandFactory {

    public Command createCommand(String command) {
        switch (command) {
        case "login":
            return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()));
        case "logout":
            return new LogoutCommand();
        case "requestRoomPage":
            return new RequestRoomPageCommand();
        case "requestsPage":
            return new RequestsPageCommand();
        case "requestRoom":
            return new RequestRoomCommand(new RequestRoomServiceImpl(new DaoHelperFactory()));
        case "main":
            return new MainCommand();
        case "RequestHandlingPage":
            return new RequestHandlingPageCommand();
        default:
            throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}
