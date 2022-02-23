package com.epam.hotelbooking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.service.DeleteRoomServiceImpl;

public class DeleteRoomCommand implements Command {
    private DeleteRoomServiceImpl deleteRoomService;

    public DeleteRoomCommand(DeleteRoomServiceImpl deleteRoomService) {
        this.deleteRoomService = deleteRoomService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long roomId = Long.parseLong(req.getParameter("roomId"));
        boolean isRoomDeleted = deleteRoomService.deleteRoom(roomId);
        return new CommandResult("controller?command=roomsPage&page=1", true);
    }
}
