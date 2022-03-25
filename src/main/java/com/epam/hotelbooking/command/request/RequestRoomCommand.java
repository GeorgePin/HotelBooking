package com.epam.hotelbooking.command.request;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RequestServiceImpl;
import com.epam.hotelbooking.validation.RequestValidator;

public class RequestRoomCommand implements Command {

    private static final String END_DATE = "endDate";
    private static final String START_DATE = "startDate";
    private final RequestServiceImpl requestService;
    private final RequestValidator requestValidator;

    public RequestRoomCommand(RequestServiceImpl requestService, RequestValidator requestValidator) {
        this.requestService = requestService;
        this.requestValidator = requestValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        Date startDate = Date.valueOf(req.getParameter(START_DATE));
        Date endDate = req.getParameter(END_DATE)
                .isEmpty() ? null : Date.valueOf(req.getParameter(END_DATE));
        Integer roomCapacity = Integer.parseInt(req.getParameter("roomCapacity"));
        String roomClass = req.getParameter("roomClass");
        Long userId = (Long) req.getSession()
                .getAttribute("userId");
        Request request = new Request.RequestBuilder().withStartDate(startDate)
                .withEndDate(endDate)
                .withRoomCapacity(roomCapacity)
                .withRoomClass(roomClass)
                .withUserId(userId)
                .build();
        if (!requestValidator.isDataForRoomRequestingValid(request)) {
            throw new ServiceException("Request data is invalid.");
        }
        requestService.createRoomRequest(request);
        return CommandResult.redirect(req.getContextPath() + "/controller?command=requestsPage&page=1");
    }

}
