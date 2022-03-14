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

public class CreateRoomCommand implements Command {
    private final RoomServiceImpl roomService;

    public CreateRoomCommand(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws DaoException, ServiceException {
        int capacity = Integer.parseInt(req.getParameter("roomCapacity"));
        int numberOfRoom = Integer.parseInt(req.getParameter("numberOfRoom"));
        RoomClass roomClass = RoomClass.valueOf(req.getParameter("roomClass")
                .toUpperCase());
        Long roomPriceId = Long.parseLong(req.getParameter("idOfPrice"));
        Room room = new Room(capacity, roomClass, numberOfRoom, roomPriceId);
        roomService.createRoom(room);
        return CommandResult.redirect("controller?command=roomsPage&page=1");
    }
}
