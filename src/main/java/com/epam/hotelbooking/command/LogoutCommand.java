package com.epam.hotelbooking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getSession()
                .setAttribute("user", null);
        req.getSession()
                .setAttribute("isLoggedIn", false);
        return new CommandResult("/index.jsp", false);
    }
}
