package com.epam.hotelbooking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.service.RequestHandlingServiceImpl;

public class RequestHandlingCommand implements Command {
    private RequestHandlingServiceImpl requestHandlingService;

    public RequestHandlingCommand(RequestHandlingServiceImpl requestHandlingService) {
        this.requestHandlingService = requestHandlingService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long requestId = Long.parseLong(req.getParameter("requestId"));
        Long roomId = Long.parseLong(req.getParameter("roomId"));
        boolean isRequestHandled = requestHandlingService.handleRoomRequest(requestId, roomId);
        return new CommandResult(req.getContextPath() + "/controller?command=requestsPage&page=1", true);
    }
}
