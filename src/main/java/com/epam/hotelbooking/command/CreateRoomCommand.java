package com.epam.hotelbooking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomClass;
import com.epam.hotelbooking.service.CreateRoomServiceImpl;

public class CreateRoomCommand implements Command {
    private CreateRoomServiceImpl createRoomService;

    public CreateRoomCommand(CreateRoomServiceImpl createRoomService) {
        this.createRoomService = createRoomService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int capacity = Integer.parseInt(req.getParameter("roomCapacity"));
        boolean isBlocked = Boolean.parseBoolean(req.getParameter("isBlocked"));
        int numberOfRoom = Integer.parseInt(req.getParameter("numberOfRoom"));
        RoomClass roomClass = RoomClass.valueOf(req.getParameter("roomClass")
                .toUpperCase());
        Long roomPriceId = Long.parseLong(req.getParameter("idOfPrice"));
        Room room = new Room(capacity, roomClass, numberOfRoom, isBlocked, roomPriceId);
        boolean isRoomCreated = createRoomService.createRoom(room);
        return new CommandResult("controller?command=roomsPage&page=1", true);
    }
}
