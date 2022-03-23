package com.epam.hotelbooking.command.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RequestServiceImpl;

public class RequestHandlingCommand implements Command {
    private RequestServiceImpl requestService;

    public RequestHandlingCommand(RequestServiceImpl requestService) {
        this.requestService = requestService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        Long requestId = Long.parseLong(req.getParameter("requestId"));
        Long roomId = Long.parseLong(req.getParameter("roomId"));
        requestService.handleRoomRequest(requestId, roomId);
        return CommandResult.redirect(req.getContextPath() + "/controller?command=requestsPage&page=1");
    }
}
