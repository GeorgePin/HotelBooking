package com.epam.hotelbooking.command.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RoomServiceImpl;

public class DeleteRoomCommand implements Command {
    private final RoomServiceImpl roomService;

    public DeleteRoomCommand(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        Long roomId = Long.parseLong(req.getParameter("roomId"));
        roomService.deleteRoom(roomId);
        return CommandResult.redirect(req.getContextPath() + "/controller?command=roomsPage&page=1");
    }
}
