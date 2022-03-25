package com.epam.hotelbooking.command.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RoomServiceImpl;
import com.epam.hotelbooking.validation.RoomValidator;

public class EditRoomCommand implements Command {

    private final RoomServiceImpl roomService;
    private final RoomValidator roomValidator;

    public EditRoomCommand(RoomServiceImpl roomService, RoomValidator roomValidator) {
        this.roomService = roomService;
        this.roomValidator = roomValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws DaoException, ServiceException {
        Integer capacity = Integer.parseInt(req.getParameter("roomCapacity"));
        Integer numberOfRoom = Integer.parseInt(req.getParameter("number"));
        String roomClass = req.getParameter("roomClass");
        Long roomPriceId = Long.parseLong(req.getParameter("idOfPrice"));
        Long roomId = Long.parseLong(req.getParameter("roomId"));
        Room room = new Room.RoomBuilder().withCapacity(capacity)
                .withNumber(numberOfRoom)
                .withRoomClass(roomClass)
                .withRoomPriceId(roomPriceId)
                .withId(roomId)
                .build();
        if (!roomValidator.isDataForRoomValid(room)) {
            throw new ServiceException("Data for room editing is invalid");
        }
        if (roomService.editRoom(room)) {
            return CommandResult.redirect(req.getContextPath() + "/controller?command=roomsPage&page=1");
        } else {
            req.setAttribute("errorMessage", "errorMessage.room-number");
            return CommandResult.forward("/controller?command=editRoomPage&roomId=" + room.getId()
                    .toString());
        }
    }

}