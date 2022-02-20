package com.epam.hotelbooking.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.entity.User;
import com.epam.hotelbooking.service.UserServiceImpl;

public class LoginCommand implements Command {
    private final UserServiceImpl userService;

    public LoginCommand(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> user = userService.login(login, password);
        if (user.isPresent()) {
            req.getSession()
                    .setAttribute("user", user.get());
            req.getSession()
                    .setAttribute("isLoggedIn", true);
            return new CommandResult("/index.jsp", false);
        } else {
            req.setAttribute("errorMessage", "Invalid login data");
            return new CommandResult("/index.jsp", false);
        }
    }
}
