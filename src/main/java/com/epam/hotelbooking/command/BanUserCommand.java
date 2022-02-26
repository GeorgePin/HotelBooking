package com.epam.hotelbooking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.service.UserServiceImpl;

public class BanUserCommand implements Command {
    private UserServiceImpl userService;

    public BanUserCommand(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long userId = Long.parseLong(req.getParameter("userId"));
        boolean isRequestHandled = userService.banUser(userId);
        return new CommandResult(req.getContextPath() + "/controller?command=clientsPage", true);
    }
}