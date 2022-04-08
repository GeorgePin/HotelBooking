package com.epam.hotelbooking.command.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RoomServiceImpl;
import com.epam.hotelbooking.validation.RoomValidator;

public class CreateRoomCommand implements Command {

    private static final String NUMBER_OF_ROOM = "numberOfRoom";
    private final RoomServiceImpl roomService;
    private final RoomValidator roomValidator;

    public CreateRoomCommand(RoomServiceImpl roomService, RoomValidator roomValidator) {
        this.roomService = roomService;
        this.roomValidator = roomValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        if (roomValidator.isNumberOfRoomStartsWithZero(req.getParameter(NUMBER_OF_ROOM))) {
            throw new ServiceException("Room number is invalid");
        }
        Integer numberOfRoom = Integer.parseInt(req.getParameter(NUMBER_OF_ROOM));
        Integer capacity = Integer.parseInt(req.getParameter("roomCapacity"));
        String roomClass = req.getParameter("roomClass");
        Long roomPriceId = Long.parseLong(req.getParameter("idOfPrice"));
        Room room = new Room.RoomBuilder().withCapacity(capacity)
                .withNumber(numberOfRoom)
                .withRoomClass(roomClass)
                .withRoomPriceId(roomPriceId)
                .build();
        if (!roomValidator.isDataForRoomValid(room)) {
            throw new ServiceException("Data for room creating is invalid");
        }
        if (roomService.createRoom(room)) {
            return CommandResult.redirect(req.getContextPath() + "/controller?command=roomsPage&page=1");
        } else {
            req.setAttribute("errorMessage", "errorMessage.room-number");
            return CommandResult.forward("/controller?command=createRoomPage");
        }
    }

}
