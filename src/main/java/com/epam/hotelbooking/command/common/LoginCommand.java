package com.epam.hotelbooking.command.common;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.UserServiceImpl;
import com.epam.hotelbooking.validation.UserValidator;

public class LoginCommand implements Command {

    private static final String MAIN_PAGE = "/index.jsp";
    private final UserServiceImpl userService;
    private final UserValidator userValidator;

    public LoginCommand(UserServiceImpl userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User userForValidation = new User.UserBuilder().withLogin(login)
                .withPassword(password)
                .build();
        if (!userValidator.isDataForLoginValid(userForValidation)) {
            throw new ServiceException("Data for logging in is invalid");
        }
        Optional<User> optionalUser = userService.login(login, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (Boolean.TRUE.equals(user.getIsBlocked())) {
                req.setAttribute("errorMessage", "errorMessage.blocked");
                return CommandResult.forward(MAIN_PAGE);
            }
            Boolean isAdmin = user.isAdmin();
            req.getSession()
                    .setAttribute("userId", user.getId());
            req.getSession()
                    .setAttribute("isLoggedIn", true);
            req.getSession()
                    .setAttribute("isAdmin", isAdmin);
            return Boolean.TRUE.equals(isAdmin)
                    ? CommandResult.redirect(req.getContextPath() + "/controller?command=requestsPage&page=1")
                    : CommandResult.redirect(req.getContextPath() + MAIN_PAGE);
        } else {
            req.setAttribute("errorMessage", "errorMessage.login");
            return CommandResult.forward(MAIN_PAGE);
        }
    }
}