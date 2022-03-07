package com.epam.hotelbooking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String name = req.getParameter("userName");
        String surname = req.getParameter("userSurname");
        String login = req.getParameter("userLogin");
        String password = req.getParameter("userPassword");
        userService.createUser(new User(name, surname, login, password));
        return CommandResult.redirect("/pages/common-pages/index.jsp");
    }
}
