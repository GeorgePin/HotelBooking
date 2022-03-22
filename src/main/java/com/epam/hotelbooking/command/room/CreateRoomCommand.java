package com.epam.hotelbooking.command.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommandResult;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomClass;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RoomServiceImpl;
import com.epam.hotelbooking.validation.RoomValidator;

public class CreateRoomCommand implements Command {
    private final RoomServiceImpl roomService;
    private final RoomValidator roomValidator = new RoomValidator();
    private static final String NUMBER_OF_ROOM = "numberOfRoom";

    public CreateRoomCommand(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws DaoException, ServiceException {
        if (!roomValidator.isDataForRoomCreatingValid(req.getParameter(NUMBER_OF_ROOM))) {
            throw new ServiceException("Data for room creating is invalid");
        }
        int capacity = Integer.parseInt(req.getParameter("roomCapacity"));
        int numberOfRoom = Integer.parseInt(req.getParameter(NUMBER_OF_ROOM));
        RoomClass roomClass = RoomClass.valueOf(req.getParameter("roomClass")
                .toUpperCase());
        Long roomPriceId = Long.parseLong(req.getParameter("idOfPrice"));
        Room room = new Room(capacity, roomClass, numberOfRoom, roomPriceId);
        if (roomService.createRoom(room)) {
            return CommandResult.redirect(req.getContextPath() + "/controller?command=roomsPage&page=1");
        } else {
            req.setAttribute("errorMessage", "errorMessage.room-number");
            return CommandResult.forward("/controller?command=createRoomPage");
        }
    }
}
