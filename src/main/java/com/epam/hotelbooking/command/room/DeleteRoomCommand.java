package com.epam.hotelbooking.command.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommandResult;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RoomServiceImpl;

public class DeleteRoomCommand implements Command {
    private final RoomServiceImpl roomService;

    public DeleteRoomCommand(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        Long roomId = Long.parseLong(req.getParameter("roomId"));
        roomService.deleteRoom(roomId);
        return CommandResult.redirect("controller?command=roomsPage&page=1");
    }
}
