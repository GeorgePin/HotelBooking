package com.epam.hotelbooking.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.UserServiceImpl;
import com.epam.hotelbooking.validation.UserValidator;

public class RegistrationCommand implements Command {

    private final UserServiceImpl userService;
    private final UserValidator userValidator;

    public RegistrationCommand(UserServiceImpl userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User.UserBuilder().withName(name)
                .withSurname(surname)
                .withLogin(login)
                .withPassword(password)
                .build();
        if (!userValidator.isDataForRegistrationValid(user)) {
            throw new ServiceException("Data for registration is invalid");
        }
        if (userService.createUser(user)) {
            return CommandResult.redirect(req.getContextPath() + "/index.jsp");
        } else {
            req.setAttribute("errorMessage", "errorMessage.registration");
            return CommandResult.forward("/registrationPage.jsp");
        }
    }

}
