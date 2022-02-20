package com.epam.hotelbooking.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.entity.User;

public class RequestHandlingCommand implements Command {
    private RequestHandlingServiceImpl requestHandlingService;

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long requestId = Long.parseLong(req.getParameter("requestId"));
        Long roomId = Long.parseLong(req.getParameter("roomId"));
        Optional<User> user = requestHandlingService.handleRequest(requestId, roomId);
        return new CommandResult("/requests.jsp", false);
    }
    // TODO на выходе должно получиться так : room blocked, in request put
    // roomId, in request put summaryPrice(calculate it by room_price x days), in
    // request is_approved true
}
