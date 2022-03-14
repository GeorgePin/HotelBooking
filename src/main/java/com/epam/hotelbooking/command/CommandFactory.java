package com.epam.hotelbooking.command;

import com.epam.hotelbooking.command.common.LoginCommand;
import com.epam.hotelbooking.command.common.LogoutCommand;
import com.epam.hotelbooking.command.common.RegistrationCommand;
import com.epam.hotelbooking.command.common.SetLanguageCommand;
import com.epam.hotelbooking.command.common.ShowPageCommand;
import com.epam.hotelbooking.command.request.RequestHandlingCommand;
import com.epam.hotelbooking.command.request.RequestHandlingPageCommand;
import com.epam.hotelbooking.command.request.RequestRoomCommand;
import com.epam.hotelbooking.command.request.RequestsPageCommand;
import com.epam.hotelbooking.command.room.CreateRoomCommand;
import com.epam.hotelbooking.command.room.CreateRoomPageCommand;
import com.epam.hotelbooking.command.room.DeleteRoomCommand;
import com.epam.hotelbooking.command.room.RoomsPageCommand;
import com.epam.hotelbooking.command.room.UnblockRoomCommand;
import com.epam.hotelbooking.command.user.BanUserCommand;
import com.epam.hotelbooking.command.user.ClientsPageCommand;
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
        case "registrationPage":
            return new ShowPageCommand();
        case "register":
            return new RegistrationCommand(new UserServiceImpl());
        case "requestsPage":
            return new RequestsPageCommand(new RequestServiceImpl());
        case "requestRoom":
            return new RequestRoomCommand(new RequestServiceImpl());
        case "requestHandling":
            return new RequestHandlingCommand(new RequestServiceImpl());
        case "requestHandlingPage":
            return new RequestHandlingPageCommand(new RequestServiceImpl(), new RoomServiceImpl());
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
        case "clientsPage":
            return new ClientsPageCommand(new UserServiceImpl());
        case "banUser":
            return new BanUserCommand(new UserServiceImpl());
        case "setLanguage":
            return new SetLanguageCommand();
        case "unblockRoom":
            return new UnblockRoomCommand(new RoomServiceImpl());
        default:
            throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}
