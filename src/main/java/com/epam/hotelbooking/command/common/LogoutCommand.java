package com.epam.hotelbooking.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;

public class LogoutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession()
                .invalidate();
        return CommandResult.redirect(req.getContextPath());
    }
}
