package com.epam.hotelbooking.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;

public class LogoutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        setAttributesOnRequest(req);
        return CommandResult.forward("/index.jsp");
    }

    private void setAttributesOnRequest(HttpServletRequest req) {
        req.getSession()
                .setAttribute("userId", null);
        req.getSession()
                .setAttribute("isLoggedIn", false);
    }
}
