package com.epam.hotelbooking.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommandResult;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.UserServiceImpl;

public class RegistrationCommand implements Command {
    private final UserServiceImpl userService;

    public RegistrationCommand(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        userService.createUser(new User(name, surname, login, password));
        return CommandResult.redirect(req.getContextPath() + "/pages/common-pages/index.jsp");
    }
}
