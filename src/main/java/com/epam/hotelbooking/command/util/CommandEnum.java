package com.epam.hotelbooking.command.util;

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
import com.epam.hotelbooking.command.room.EditRoomCommand;
import com.epam.hotelbooking.command.room.EditRoomPageCommand;
import com.epam.hotelbooking.command.room.RoomsPageCommand;
import com.epam.hotelbooking.command.room.SetRoomStateCommand;
import com.epam.hotelbooking.command.user.ClientsPageCommand;
import com.epam.hotelbooking.command.user.SetUserStateCommand;
import com.epam.hotelbooking.service.RequestServiceImpl;
import com.epam.hotelbooking.service.RoomPriceServiceImpl;
import com.epam.hotelbooking.service.RoomServiceImpl;
import com.epam.hotelbooking.service.UserServiceImpl;
import com.epam.hotelbooking.validation.RequestValidator;
import com.epam.hotelbooking.validation.RoomValidator;
import com.epam.hotelbooking.validation.UserValidator;

enum CommandEnum {

    LOGIN(new LoginCommand(new UserServiceImpl(), new UserValidator())),
    LOGOUT(new LogoutCommand()),
    REGISTER(new RegistrationCommand(new UserServiceImpl(), new UserValidator())),
    REQUESTSPAGE(new RequestsPageCommand(new RequestServiceImpl())),
    REQUESTROOM(new RequestRoomCommand(new RequestServiceImpl(), new RequestValidator())),
    REQUESTHANDLING(new RequestHandlingCommand(new RequestServiceImpl())),
    REQUESTHANDLINGPAGE(new RequestHandlingPageCommand(new RequestServiceImpl(), new RoomServiceImpl())),
    ROOMSPAGE(new RoomsPageCommand(new RoomServiceImpl())),
    DELETEROOM(new DeleteRoomCommand(new RoomServiceImpl())),
    CREATEROOMPAGE(new CreateRoomPageCommand(new RoomPriceServiceImpl())),
    CREATEROOM(new CreateRoomCommand(new RoomServiceImpl(), new RoomValidator())),
    SHOWPAGE(new ShowPageCommand()),
    CLIENTSPAGE(new ClientsPageCommand(new UserServiceImpl())),
    SETUSERSTATE(new SetUserStateCommand(new UserServiceImpl())),
    SETLANGUAGE(new SetLanguageCommand()),
    SETROOMSTATE(new SetRoomStateCommand(new RoomServiceImpl())),
    EDITROOMPAGE(new EditRoomPageCommand(new RoomPriceServiceImpl(), new RoomServiceImpl())),
    EDITROOM(new EditRoomCommand(new RoomServiceImpl(),new RoomValidator()));

    CommandEnum(Command command) {
        this.command = command;
    }

    private final Command command;

    protected Command getCommand() {
        return this.command;
    }

}
