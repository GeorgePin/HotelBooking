package com.epam.hotelbooking.command;

import com.epam.hotelbooking.service.RequestServiceImpl;
import com.epam.hotelbooking.service.RoomPriceServiceImpl;
import com.epam.hotelbooking.service.RoomServiceImpl;
import com.epam.hotelbooking.service.UserServiceImpl;

public class CommandFactory {

    public Command createCommand(String command) {
        switch (command) {
        case "login":
            return new LoginCommand(new UserServiceImpl());
        case "logout":
            return new LogoutCommand();
        case "requestsPage":
            return new RequestsPageCommand(new RequestServiceImpl());
        case "requestRoom":
            return new RequestRoomCommand(new RequestServiceImpl());
        case "requestHandling":
            return new RequestHandlingCommand(new RequestServiceImpl());
        case "requestHandlingPage":
            return new RequestHandlingPageCommand(new RequestServiceImpl(), new RoomServiceImpl(),
                    new RoomPriceServiceImpl());
        case "roomsPage":
            return new RoomsPageCommand(new RoomServiceImpl());
        case "deleteRoom":
            return new DeleteRoomCommand(new RoomServiceImpl());
        case "createRoomPage":
            return new CreateRoomPageCommand(new RoomPriceServiceImpl());
        case "createRoom":
            return new CreateRoomCommand(new RoomServiceImpl());
        case "showPage":
            return new ShowPageCommand();
        case "registrationPage":
            return new ShowPageCommand();
        case "clientsPage":
            return new ClientsPageCommand(new UserServiceImpl());
        case "registration":
            return new RegistrationCommand(new UserServiceImpl());
        case "banUser":
            return new BanUserCommand(new UserServiceImpl());
        case "setLanguage":
            return new SetLanguageCommand();
        default:
            throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}
