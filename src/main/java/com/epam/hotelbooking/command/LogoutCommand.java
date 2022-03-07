package com.epam.hotelbooking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession()
                .setAttribute("userId", null);
        req.getSession()
                .setAttribute("isLoggedIn", false);
        return CommandResult.forward("/pages/common-pages/index.jsp");
    }
}
