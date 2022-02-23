package com.epam.hotelbooking.command;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.service.RequestRoomServiceImpl;

public class RequestRoomCommand implements Command {
    private final RequestRoomServiceImpl requestRoomService;

    public RequestRoomCommand(RequestRoomServiceImpl requestRoomService) {
        this.requestRoomService = requestRoomService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int roomCapacity = Integer.parseInt(req.getParameter("roomCapacity"));
        String roomClass = req.getParameter("roomClass");
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date endDate = Date.valueOf(req.getParameter("endDate"));
        Long userId = (Long) req.getSession().getAttribute("user");
        //TODO insert entity
        boolean hasRoomRequestCreated = requestRoomService.createRoomRequest(roomCapacity, roomClass, startDate,
                endDate, userId);
        if (hasRoomRequestCreated) {
            return new CommandResult("/index.jsp", false);
        } else {
            req.setAttribute("errorMessage", "Invalid login data");
            return new CommandResult("/index.jsp", false);
        }
    }
}
