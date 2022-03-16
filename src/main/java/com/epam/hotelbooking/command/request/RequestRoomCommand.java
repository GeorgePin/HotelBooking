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
import com.epam.hotelbooking.validation.RequestValidator;

public class RequestRoomCommand implements Command {
    private final RequestServiceImpl requestService;
    private final RequestValidator requestValidator = new RequestValidator();
    private static final String END_DATE = "endDate";
    private static final String START_DATE = "startDate";

    public RequestRoomCommand(RequestServiceImpl requestService) {
        this.requestService = requestService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        if (!requestValidator.isDataForRoomRequestingValid(req.getParameter(START_DATE), req.getParameter(END_DATE))) {
            throw new ServiceException("End date is before start date or date is invalid");
        }
        Date startDate = Date.valueOf(req.getParameter(START_DATE));
        Date endDate = req.getParameter(END_DATE)
                .isEmpty() ? null : Date.valueOf(req.getParameter(END_DATE));
        int roomCapacity = Integer.parseInt(req.getParameter("roomCapacity"));
        RoomClass roomClass = RoomClass.valueOf(req.getParameter("roomClass")
                .toUpperCase());
        Long userId = (Long) req.getSession()
                .getAttribute("userId");
        requestService.createRoomRequest(new Request(userId, startDate, endDate, roomCapacity, roomClass));
        return CommandResult.redirect(req.getContextPath() + "/controller?command=requestsPage&page=1");

    }
}
