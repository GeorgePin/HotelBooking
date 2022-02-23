package com.epam.hotelbooking.command;

import com.epam.hotelbooking.dao.DaoHelperFactory;
import com.epam.hotelbooking.service.AmountOfRequestsServiceImpl;
import com.epam.hotelbooking.service.AmountOfRoomsServiceImpl;
import com.epam.hotelbooking.service.CreateRoomServiceImpl;
import com.epam.hotelbooking.service.CreateUserServiceImpl;
import com.epam.hotelbooking.service.DeleteRoomServiceImpl;
import com.epam.hotelbooking.service.RequestHandlingServiceImpl;
import com.epam.hotelbooking.service.RequestRoomServiceImpl;
import com.epam.hotelbooking.service.RequestsPageServiceImpl;
import com.epam.hotelbooking.service.RoomPriceServiceImpl;
import com.epam.hotelbooking.service.RoomsPageServiceImpl;
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
            return new RequestsPageCommand(new RequestsPageServiceImpl(new DaoHelperFactory()),
                    new AmountOfRequestsServiceImpl(new DaoHelperFactory()));
        case "requestRoom":
            return new RequestRoomCommand(new RequestRoomServiceImpl(new DaoHelperFactory()));
        case "main":
            return new MainCommand();
        case "RequestHandlingPage":
            return new RequestHandlingPageCommand();
        case "RequestHandling":
            return new RequestHandlingCommand(new RequestHandlingServiceImpl(new DaoHelperFactory()));
        case "roomsPage":
            return new RoomsPageCommand(new RoomsPageServiceImpl(new DaoHelperFactory()),
                    new AmountOfRoomsServiceImpl(new DaoHelperFactory()));
        case "deleteRoom":
            return new DeleteRoomCommand(new DeleteRoomServiceImpl(new DaoHelperFactory()));
        case "createRoomPage":
            return new CreateRoomPageCommand(new RoomPriceServiceImpl(new DaoHelperFactory()));
        case "createRoom":
            return new CreateRoomCommand(new CreateRoomServiceImpl(new DaoHelperFactory()));
        case "registrationPage":
            return new RegistrationPageCommand();
        case "registration":
            return new RegistrationCommand(new CreateUserServiceImpl(new DaoHelperFactory()));
        default:
            throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}
