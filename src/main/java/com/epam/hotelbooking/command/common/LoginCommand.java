package com.epam.hotelbooking.command.common;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommandResult;
import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.UserServiceImpl;

public class LoginCommand implements Command {
    private final UserServiceImpl userService;

    public LoginCommand(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, DaoException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> user = userService.login(login, password);
        if (user.isPresent()) {
            boolean isAdmin = user.get()
                    .isAdmin();
            req.getSession()
                    .setAttribute("userId", user.get()
                            .getId());
            req.getSession()
                    .setAttribute("isLoggedIn", true);
            req.getSession()
                    .setAttribute("isAdmin", isAdmin);
            return isAdmin ? CommandResult.forward("/controller?command=requestsPage&page=1")
                    : CommandResult.forward("/pages/common-pages/index.jsp");
        } else {
            req.setAttribute("errorMessage", "errorMessage.login");
            return CommandResult.forward("/pages/common-pages/index.jsp");
        }
    }
}
