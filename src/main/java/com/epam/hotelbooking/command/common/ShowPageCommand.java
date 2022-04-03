package com.epam.hotelbooking.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;

public class ShowPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String pageName = req.getParameter("page");
        return CommandResult.forward("/" + pageName);
    }
}
