package com.epam.hotelbooking.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.Command;
import com.epam.hotelbooking.command.CommandResult;

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
