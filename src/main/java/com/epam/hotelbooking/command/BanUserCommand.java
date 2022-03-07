package com.epam.hotelbooking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.UserServiceImpl;

public class BanUserCommand implements Command {
    private final UserServiceImpl userService;

    public BanUserCommand(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        userService.banUser(userId);
        return CommandResult.redirect(req.getContextPath() + "/controller?command=clientsPage");
    }
}