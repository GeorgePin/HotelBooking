package com.epam.hotelbooking.command.request;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommandResult;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.entity.RoomClass;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RequestServiceImpl;

public class RequestRoomCommand implements Command {
    private final RequestServiceImpl requestService;

    public RequestRoomCommand(RequestServiceImpl requestService) {
        this.requestService = requestService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        int roomCapacity = Integer.parseInt(req.getParameter("roomCapacity"));
        RoomClass roomClass = RoomClass.valueOf(req.getParameter("roomClass")
                .toUpperCase());
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date endDate = Date.valueOf(req.getParameter("endDate"));
        Long userId = (Long) req.getSession()
                .getAttribute("userId");
        requestService.createRoomRequest(new Request(userId, startDate, endDate, roomCapacity, roomClass));
        return CommandResult.redirect("pages/common-pages/index.jsp");

    }
}
