package com.epam.hotelbooking.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommandResult;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.UserServiceImpl;
import com.epam.hotelbooking.validation.UserValidator;

public class RegistrationCommand implements Command {
    private final UserServiceImpl userService;
    private final UserValidator userValidator = new UserValidator();

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
        User user = new User(name, surname, login, password);
        if (!userValidator.isDataForRegistrationValid(user)) {
            throw new ServiceException("Data for registration is invalid");
        }
        userService.createUser(user);
        return CommandResult.redirect(req.getContextPath() + "/index.jsp");
    }
}
